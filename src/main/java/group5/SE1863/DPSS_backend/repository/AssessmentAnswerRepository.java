package group5.SE1863.DPSS_backend.repository;

import group5.SE1863.DPSS_backend.entity.AssessmentAnswer;
import group5.SE1863.DPSS_backend.entity.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssessmentAnswerRepository extends JpaRepository<AssessmentAnswer, Long> {
    List<AssessmentAnswer> findByResult(AssessmentResult result);


}
