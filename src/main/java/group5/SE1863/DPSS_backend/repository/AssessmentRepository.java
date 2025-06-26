package group5.SE1863.DPSS_backend.repository;

import group5.SE1863.DPSS_backend.entity.Assessment;
import group5.SE1863.DPSS_backend.entity.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssessmentRepository extends JpaRepository<Assessment, Long> {
}
