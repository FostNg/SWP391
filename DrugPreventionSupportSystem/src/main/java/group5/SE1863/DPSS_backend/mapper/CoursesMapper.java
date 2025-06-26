package group5.SE1863.DPSS_backend.mapper;

import group5.SE1863.DPSS_backend.dto.request.CoursesRequest;
import group5.SE1863.DPSS_backend.dto.response.CoursesResponse;
import group5.SE1863.DPSS_backend.entity.Courses;
import org.springframework.stereotype.Component;

@Component
public class CoursesMapper {
    public static Courses mapToCourse(CoursesRequest request) {
        Courses course = new Courses();
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setStartDate(request.getStartDate());
        course.setPrice(request.getPrice());

        // imgUrl, videoUrl sẽ được set sau khi upload Firebase
        return course;
    }
    public static CoursesResponse mapToCourseResponse(Courses course) {
        return new CoursesResponse(
                course.getCourseId(),
                course.getTitle(),
                course.getDescription(),
                course.getStartDate(),
                course.getPrice(),
                course.getImgUrl(),
                course.getVideoUrl()
        );
    }
}
