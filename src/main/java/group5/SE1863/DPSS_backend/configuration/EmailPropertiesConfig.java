package group5.SE1863.DPSS_backend.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "email")
@Slf4j
public class EmailPropertiesConfig {
    private static boolean passwordResetEnabled = true;


    public void setPasswordResetEnabled(boolean passwordResetEnabled) {
        EmailPropertiesConfig.passwordResetEnabled = passwordResetEnabled;
        log.info("Password reset enabled: {}", passwordResetEnabled);
    }
}
