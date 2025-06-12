package group5.SE1863.DPSS_backend.service;

import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import group5.SE1863.DPSS_backend.dto.request.LogoutRequest;
import group5.SE1863.DPSS_backend.dto.request.VerifyTokenRequest;
import group5.SE1863.DPSS_backend.dto.response.TokenResponse;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import group5.SE1863.DPSS_backend.dto.request.AuthenticationRequest;
import group5.SE1863.DPSS_backend.dto.response.AuthenticationResponse;
import group5.SE1863.DPSS_backend.dto.response.TrackingUserResponse;
import group5.SE1863.DPSS_backend.dto.response.VerifyTokenResponse;
import group5.SE1863.DPSS_backend.entity.InvalidatedToken;
import group5.SE1863.DPSS_backend.entity.TrackingUserLogin;
import group5.SE1863.DPSS_backend.entity.User;
import group5.SE1863.DPSS_backend.exception.AppException;
import group5.SE1863.DPSS_backend.exception.ErrorCode;
import group5.SE1863.DPSS_backend.repository.InvalidatedTokenRepository;
import group5.SE1863.DPSS_backend.repository.TrackingUserRepository;
import group5.SE1863.DPSS_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final InvalidatedTokenRepository invalidatedTokenRepository;
    private final TrackingUserRepository trackingUserRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNAL_KEY;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        var user = userRepository.findByUserName(authenticationRequest.getUserName()).orElseThrow(() -> {
            return new AppException(ErrorCode.USER_NOT_EXISTED);
        });
        boolean status = user.isStatus();

        // Check password
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());
        if (!authenticated || !status) throw new AppException(ErrorCode.UNAUTHENDICATED);

        // Create authentication response
        TokenResponse token = generateToken(user);
        AuthenticationResponse authRes = new AuthenticationResponse();

        authRes.setToken(token.getToken());
        authRes.setAuthenticated(true);
        trackingLogin(user, token.getExpirationTime());
        return authRes;
    }

    private TokenResponse generateToken(User user) {
        // Create a new TokenResponse
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        // Create payload
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder().subject(String.valueOf(user.getUserId())).issuer("DPSS_backend.com").issueTime(new Date()).expirationTime(new Date(
                        //Set time for token
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli() // expired in 1 hour
                )).jwtID(UUID.randomUUID().toString()) // Set JWT ID
                .claim("scope", buildScope(user)) // Add scope
                .build();
        // Set token in tokenResponse
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        try {
            jwsObject.sign(new MACSigner(SIGNAL_KEY.getBytes()));
            return new TokenResponse(jwsObject.serialize(), jwtClaimsSet.getExpirationTime());
        } catch (JOSEException e) {
            log.error("Cannot create token", e);
            throw new RuntimeException(e);
        }
    }

    public VerifyTokenResponse verifyToken(VerifyTokenRequest verifyTokenRequest) throws ParseException, JOSEException {
        var token = verifyTokenRequest.getToken();
        VerifyTokenResponse response = new VerifyTokenResponse();
        boolean isValid = true;
        try {
            tokenCheck(token);
        } catch (AppException e) {
            isValid = false;
        }
        response.setValid(isValid);
        return response;
    }

    private String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            //Role define to be a set so we make it in to a string and separate by " "
            user.getRoles().forEach(roles -> {
                stringJoiner.add(roles.getRoleType());
            });

        }
        return stringJoiner.toString(); // Replace with actual token generation
    }

    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        try {
            String token = request.getToken();
            SignedJWT signedJWT = SignedJWT.parse(token);
            String userId = signedJWT.getJWTClaimsSet().getSubject();

            if (userId != null) {
                Long userIdBefore = Long.parseLong(userId);
                System.out.println(userId);
                trackingUserRepository.deleteByUserId(userIdBefore);
            }
        } catch (Exception e) {
            throw new AppException(ErrorCode.INVALID_INFOMATION);
        }

        var signToken = tokenCheck(request.getToken());
        // Extract JWT ID and expiration time from the token
        String jit = signToken.getJWTClaimsSet().getJWTID();
        Date expiredTime = signToken.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidToken = new InvalidatedToken();
        invalidToken.setTokenId(jit);
        invalidToken.setTimeExpired(expiredTime);

        invalidatedTokenRepository.save(invalidToken);
    }

    private SignedJWT tokenCheck(String token) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNAL_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expityTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);
        // Check if the token is verified and not expired
        if (!verified || expityTime.after(new Date())) {
            throw new AppException(ErrorCode.UNAUTHENDICATED);
        }
        if (invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())) {
            throw new AppException(ErrorCode.UNAUTHENDICATED);
        }
        return signedJWT;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    private void trackingLogin(User user, Date expiredTime) {
        TrackingUserLogin trackingUserLogin = new TrackingUserLogin();
        trackingUserLogin.setUserId(user.getUserId());
        trackingUserLogin.setLoginTime(new Date());
        trackingUserLogin.setExpiredTime(expiredTime);// Set expiration time for 1 hour
        trackingUserRepository.save(trackingUserLogin);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public TrackingUserResponse countLoginUser() {
        Set<Long> uniqueUserIds = new HashSet<>();
        List<TrackingUserLogin> trackingUserLogins = trackingUserRepository.findAll();
        for (TrackingUserLogin trackingUserLogin : trackingUserLogins) {
            uniqueUserIds.add(trackingUserLogin.getUserId());
        }
        Long count = Long.valueOf(uniqueUserIds.size());
        TrackingUserResponse trackingUserResponse = new TrackingUserResponse();
        trackingUserResponse.setUserCount(count);
        return trackingUserResponse;
    }
}
