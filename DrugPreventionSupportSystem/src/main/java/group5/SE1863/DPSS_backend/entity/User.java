package group5.SE1863.DPSS_backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
<<<<<<< HEAD

import java.time.LocalDate;
import java.util.List;
=======
import java.time.LocalDate;
>>>>>>> 894443c9ff4b67cf1ef6c3069f10aad3f5892c01
import java.util.Set;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    @Column(name = "user_id")
=======
>>>>>>> 894443c9ff4b67cf1ef6c3069f10aad3f5892c01
    private Long userId;

    @Column(name = "username", unique = true, columnDefinition = "VARCHAR(255)")
    private String userName;

    private String password;

    private String email;

    private String fullName;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dayOfBirth;

    private boolean status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleDetail> roles;

<<<<<<< HEAD
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_courses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Courses> enrolledCourses;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "created_by")
//    private User createdBy;

=======
>>>>>>> 894443c9ff4b67cf1ef6c3069f10aad3f5892c01

}
