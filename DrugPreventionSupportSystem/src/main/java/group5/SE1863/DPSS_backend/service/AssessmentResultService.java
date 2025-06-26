package group5.SE1863.DPSS_backend.service;

import group5.SE1863.DPSS_backend.dto.request.AssessmentSubmitRequest;
import group5.SE1863.DPSS_backend.dto.response.AssessmentResultResponse;
import group5.SE1863.DPSS_backend.entity.*;
import group5.SE1863.DPSS_backend.exception.AppException;
import group5.SE1863.DPSS_backend.exception.ErrorCode;
import group5.SE1863.DPSS_backend.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AssessmentResultService {
    private final AssessmentRepository assessmentRepository;
    private final UserRepository userRepository;
    private final AssessmentResultRepository assessmentResultRepository;
    private final AssessmentOptionRepository assessmentOptionRepository;
    private final AssessmentAnswerRepository assessmentAnswerRepository;

    public AssessmentResultResponse submitAssessment(Long assessmentId, AssessmentSubmitRequest request, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_USERID));
        Assessment assessment = assessmentRepository.findById(assessmentId)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_ASSESSMENT_ID));
        BigDecimal totalScore = BigDecimal.ZERO;
        List<AssessmentAnswer> answerList = new ArrayList<>();
        for (AssessmentSubmitRequest.AnsweredQuestion answer : request.getAnswers()) {
            AssessmentQuestion question = assessmentOptionRepository.findById(answer.getQuestionId())
                    .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND)).getQuestion();

            AssessmentOption selectedOption = assessmentOptionRepository.findById(answer.getSelectedOptionId())
                    .orElseThrow(() -> new AppException(ErrorCode.INVALID_ANSWER));
            AssessmentAnswer assessmentAnswer = new AssessmentAnswer();
            assessmentAnswer.setQuestion(question);
            assessmentAnswer.setSelectedOption(selectedOption);
            answerList.add(assessmentAnswer);
            totalScore = totalScore.add(BigDecimal.valueOf(selectedOption.getScore()));
        }
        AssessmentResult result = new AssessmentResult();
        result.setAssessment(assessment);
        result.setUser(user);
        result.setScore(totalScore);
        result.setTakeTime(LocalDateTime.now());
        result.setResultName("Result for " + assessment.getAssessmentName());
        result.setAnswers(new ArrayList<>());
        AssessmentResult savedResult = assessmentResultRepository.save(result);
        for (AssessmentAnswer answer : answerList) {
            answer.setResult(savedResult);
            assessmentAnswerRepository.save(answer);
        }

        return new AssessmentResultResponse(
                assessment.getAssessmentName(),
                result.getResultName(),
                result.getScore(),
                result.getTakeTime()
        );
    }
}
