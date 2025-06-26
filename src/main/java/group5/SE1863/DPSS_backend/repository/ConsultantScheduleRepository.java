package group5.SE1863.DPSS_backend.repository;

import group5.SE1863.DPSS_backend.entity.ConsultantSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsultantScheduleRepository extends JpaRepository<ConsultantSchedule, Long> {
    List<ConsultantSchedule> findByDate(LocalDate date);
}
