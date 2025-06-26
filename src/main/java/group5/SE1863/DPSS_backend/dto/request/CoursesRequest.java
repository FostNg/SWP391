package group5.SE1863.DPSS_backend.dto.request;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoursesRequest {
    private String title;
    private String description;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate startDate; // Start date of the course
    private double price; // Price of the course

}
