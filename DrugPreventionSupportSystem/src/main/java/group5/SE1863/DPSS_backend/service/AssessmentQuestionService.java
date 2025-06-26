package group5.SE1863.DPSS_backend.service;

import group5.SE1863.DPSS_backend.dto.response.AssessmentOptionResponse;
import group5.SE1863.DPSS_backend.dto.response.AssessmentQuestionResponse;
import group5.SE1863.DPSS_backend.entity.AssessmentQuestion;
import group5.SE1863.DPSS_backend.repository.AssessmentQuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AssessmentQuestionService {
    private final AssessmentQuestionRepository assessmentQuestionRepository;

    public List<AssessmentQuestionResponse> getQuestionsByAssessmentId(Long assessmentId) {
        List<AssessmentQuestion> questions = assessmentQuestionRepository.findByAssessment_AssessmentID(assessmentId);
        return questions.stream().map(question -> new AssessmentQuestionResponse(
                question.getQuestionID(),
                question.getQuestionText(),
                question.getOptions().stream().map(opt -> new AssessmentOptionResponse(
                        opt.getOptionID(),
                        opt.getOptionText(),
                        opt.getScore()
                )).collect(Collectors.toList())
        )).collect(Collectors.toList());
    }
}
