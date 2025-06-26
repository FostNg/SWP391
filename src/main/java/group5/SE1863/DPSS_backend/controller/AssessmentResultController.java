package group5.SE1863.DPSS_backend.controller;

import group5.SE1863.DPSS_backend.dto.request.AssessmentSubmitRequest;
import group5.SE1863.DPSS_backend.dto.response.AssessmentQuestionResponse;
import group5.SE1863.DPSS_backend.dto.response.AssessmentResultResponse;
import group5.SE1863.DPSS_backend.entity.ApiResponse;
import group5.SE1863.DPSS_backend.service.AssessmentQuestionService;
import group5.SE1863.DPSS_backend.service.AssessmentResultService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/assessments-results")
public class AssessmentResultController {
    private final AssessmentResultService assessmentResultService;
    private final AssessmentQuestionService assessmentQuestionService;

    @PostMapping("/{assessmentId}/submit")
    @PreAuthorize("hasRole('USER')")
    public ApiResponse<AssessmentResultResponse> submitAssessment(
            @PathVariable Long assessmentId,
            @RequestBody AssessmentSubmitRequest request
    ) {
        Long userId = Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
        AssessmentResultResponse response = assessmentResultService.submitAssessment(assessmentId, request, userId);
        return ApiResponse.success(response);
    }
    @GetMapping("/{assessmentId}/my-result")
    public ApiResponse<AssessmentResultResponse> getMyAssessmentResult(@PathVariable Long assessmentId) {
        Long userId = Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());

        AssessmentResultResponse response = assessmentResultService.submitAssessment(assessmentId, null, userId);

        return ApiResponse.success(response);
    }
    @GetMapping("/{assessmentId}/questions")
    @PreAuthorize("hasAnyRole('STAFF', 'USER')")
    public ApiResponse<List<AssessmentQuestionResponse>> getQuestions(@PathVariable Long assessmentId) {
        return ApiResponse.success(assessmentQuestionService.getQuestionsByAssessmentId(assessmentId));
    }
}
