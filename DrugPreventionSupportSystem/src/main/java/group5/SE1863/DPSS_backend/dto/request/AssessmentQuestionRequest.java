package group5.SE1863.DPSS_backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentQuestionRequest {
    private String questionText;
    private String answerType; // e.g., "text", "multiple-choice"
    private List<String> options; // optional, only used for choice-type questions
    private boolean isRequired;
}
