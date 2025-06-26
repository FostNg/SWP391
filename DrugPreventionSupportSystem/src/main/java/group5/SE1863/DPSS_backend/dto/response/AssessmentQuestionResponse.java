package group5.SE1863.DPSS_backend.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AssessmentQuestionResponse {
    private Long questionId; // ID of the question
    private String questionText; // Text of the question
    private String answerType; // Type of answer (e.g., "text", "multiple-choice")
    private String[] options; // Options for multiple-choice questions, if applicable
    private boolean isRequired; // Whether the question is required or optional

    public <R> AssessmentQuestionResponse(Long questionID, String questionText, R collect) {
    }
}
