package group5.SE1863.DPSS_backend.repository;

import group5.SE1863.DPSS_backend.entity.ConsultationAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<ConsultationAppointment, Long> {
    List<ConsultationAppointment> findByUser_UserId(Long userId);
    List<ConsultationAppointment> findByStaff_StaffId(Long staffId);
}
