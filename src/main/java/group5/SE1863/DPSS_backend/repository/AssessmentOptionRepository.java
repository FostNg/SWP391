package group5.SE1863.DPSS_backend.repository;

import group5.SE1863.DPSS_backend.entity.AssessmentOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssessmentOptionRepository extends JpaRepository<AssessmentOption, Long> {
    List<AssessmentOption> findByQuestion_QuestionID(Long questionId);

}
