package group5.SE1863.DPSS_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AssessmentQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionID;
    private String answerType; // e.g., "text", "multiple-choice"
    @Column(nullable = false)
    private String questionText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assessmentID", nullable = false)
    private Assessment assessment;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AssessmentOption> options = new ArrayList<>();
}
