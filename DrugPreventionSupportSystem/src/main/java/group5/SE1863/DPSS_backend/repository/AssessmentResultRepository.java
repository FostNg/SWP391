package group5.SE1863.DPSS_backend.repository;

import group5.SE1863.DPSS_backend.entity.AssessmentResult;
import group5.SE1863.DPSS_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssessmentResultRepository extends JpaRepository<AssessmentResult, Long> {
    // Custom query methods can be added here if needed
    // For example, to find results by user or assessment
    List<AssessmentResult> findByUser(User user);
}
