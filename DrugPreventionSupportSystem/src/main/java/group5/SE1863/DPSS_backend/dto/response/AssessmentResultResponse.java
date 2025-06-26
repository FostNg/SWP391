package group5.SE1863.DPSS_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentResultResponse {
    private String assessmentName;
    private String resultName;
    private BigDecimal score;
    private LocalDateTime takeTime;

}
