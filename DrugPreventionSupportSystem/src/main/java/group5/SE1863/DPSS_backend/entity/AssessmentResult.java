package group5.SE1863.DPSS_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AssessmentResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultID;

    @ManyToOne
    @JoinColumn(name = "assessmentID", nullable = false)
    private Assessment assessment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String resultName;

    private BigDecimal score;
    private LocalDateTime takeTime;

    @OneToMany(mappedBy = "result", cascade = CascadeType.ALL)
    private List<AssessmentAnswer> answers;

}
