package group5.SE1863.DPSS_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    private String title;
    private String description;
    private LocalDate startDate;
    private double price;
    private String imgUrl;
    private String videoUrl;


    @ManyToMany(mappedBy = "enrolledCourses")
    private List<User> enrolledUsers;

}
