package group5.SE1863.DPSS_backend.repository;

import group5.SE1863.DPSS_backend.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

}
