package group5.SE1863.DPSS_backend.controller;

import group5.SE1863.DPSS_backend.dto.request.CoursesRequest;
import group5.SE1863.DPSS_backend.dto.response.CoursesResponse;
import group5.SE1863.DPSS_backend.entity.ApiResponse;
import group5.SE1863.DPSS_backend.entity.Courses;
import group5.SE1863.DPSS_backend.service.CoursesService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/courses")
public class CoursesController {
    private final CoursesService coursesService;

    @PostMapping("/{courseId}/register")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ApiResponse<CoursesResponse>> registerForCourse(@PathVariable Long courseId) {
        // Lấy userId từ token hiện tại (Authentication)
        Long userId = Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());

        CoursesResponse response = coursesService.registerCourse(courseId, userId);

        ApiResponse<CoursesResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(response);

        return ResponseEntity.ok(apiResponse);
    }
}
