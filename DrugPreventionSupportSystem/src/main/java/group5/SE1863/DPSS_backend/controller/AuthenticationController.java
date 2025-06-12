package group5.SE1863.DPSS_backend.controller;

import com.nimbusds.jose.JOSEException;
import group5.SE1863.DPSS_backend.dto.request.AuthenticationRequest;
import group5.SE1863.DPSS_backend.dto.request.VerifyTokenRequest;
import group5.SE1863.DPSS_backend.dto.response.AuthenticationResponse;
import group5.SE1863.DPSS_backend.dto.response.VerifyTokenResponse;
import group5.SE1863.DPSS_backend.entity.ApiResponse;
import group5.SE1863.DPSS_backend.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticate(request);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        authenticationResponse.setAuthenticated(result.isAuthenticated());
        authenticationResponse.setToken(result.getToken());

        ApiResponse<AuthenticationResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(authenticationResponse);

        return apiResponse;
    }
    @PostMapping("/verifyToken")
    ApiResponse<VerifyTokenResponse> authenticate(@RequestBody VerifyTokenRequest request) throws ParseException, JOSEException {
        var result = authenticationService.verifyToken(request);
        VerifyTokenResponse verifyTokenResponse = new VerifyTokenResponse();
        verifyTokenResponse.setValid(result.isValid());
        ApiResponse<VerifyTokenResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(verifyTokenResponse);
        return apiResponse;
    }
}
