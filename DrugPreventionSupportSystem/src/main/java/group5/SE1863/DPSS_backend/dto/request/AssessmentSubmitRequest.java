package group5.SE1863.DPSS_backend.dto.request;

import lombok.*;
import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentSubmitRequest {
    private List<AnsweredQuestion> answers;

    @Data
    public static class AnsweredQuestion {
        private Long questionId;
        private Long selectedOptionId;
    }
}
