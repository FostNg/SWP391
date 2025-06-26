package group5.SE1863.DPSS_backend.repository;

import group5.SE1863.DPSS_backend.entity.AssessmentQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AssessmentQuestionRepository extends JpaRepository<AssessmentQuestion, Long> {
    List<AssessmentQuestion> findByAssessment_AssessmentID(Long assessmentId);
}
