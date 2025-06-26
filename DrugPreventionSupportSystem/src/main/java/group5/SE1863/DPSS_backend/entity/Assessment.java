package group5.SE1863.DPSS_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Assessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assessmentID;

    private String assessmentName;
    private String description;

//    @OneToMany(mappedBy = "assessment", cascade = CascadeType.ALL)
//    private List<AssessmentResult> results;
}
