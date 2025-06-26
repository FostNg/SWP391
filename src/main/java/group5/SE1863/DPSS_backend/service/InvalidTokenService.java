package group5.SE1863.DPSS_backend.service;

import group5.SE1863.DPSS_backend.repository.InvalidatedTokenRepository;
import group5.SE1863.DPSS_backend.repository.TrackingUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class InvalidTokenService {

    @Autowired
    private InvalidatedTokenRepository invalidatedTokenRepository;
    @Autowired
    private TrackingUserRepository trackingUserRepository;

    public void deleteExpiredTime() {
        Date currentTime = new Date();
        invalidatedTokenRepository.deleteExpiredTokens(currentTime);
    }
}
