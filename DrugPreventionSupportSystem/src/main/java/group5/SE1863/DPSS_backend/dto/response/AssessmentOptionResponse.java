package group5.SE1863.DPSS_backend.dto.response;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssessmentOptionResponse {
    private Long optionId;
    private String optionText;
    private int score;
}
