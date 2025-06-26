package group5.SE1863.DPSS_backend.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoursesResponse {
    private Long courseId;
    private String title;
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") // Giúp format đúng khi trả về
    private LocalDate startDate;
    
    private double price; // Price of the course
    private String imgUrl; // URL of the course image
    private String videoUrl;
}
