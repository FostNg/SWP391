package group5.SE1863.DPSS_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class TokenResponse {
    private String token;
    private Date expirationTime;

}
