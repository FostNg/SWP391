package group5.SE1863.DPSS_backend.service;


import group5.SE1863.DPSS_backend.dto.response.CoursesResponse;
import group5.SE1863.DPSS_backend.entity.Courses;
import group5.SE1863.DPSS_backend.entity.User;
import group5.SE1863.DPSS_backend.exception.AppException;
import group5.SE1863.DPSS_backend.exception.ErrorCode;
import group5.SE1863.DPSS_backend.repository.CourseRepository;
import group5.SE1863.DPSS_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoursesService {
    private final CourseRepository courseRepository;
    private UserRepository userRepository;

    @PreAuthorize("hasRole('ROLE_USER')")
    public CoursesResponse registerCourse(Long courseId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_USERID));

        Courses course = courseRepository.findById(courseId)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_COURSEID));

        if (course.getEnrolledUsers().contains(user)) {
            throw new AppException(ErrorCode.ALREADY_REGISTERED);
        }

        course.getEnrolledUsers().add(user);
        courseRepository.save(course);

        return new CoursesResponse(
                course.getCourseId(),
                course.getTitle(),
                course.getDescription(),
                course.getStartDate(),
                course.getPrice(),
                course.getImgUrl(),
                course.getVideoUrl());
    }

}
