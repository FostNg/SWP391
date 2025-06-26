package group5.SE1863.DPSS_backend.repository;

import group5.SE1863.DPSS_backend.entity.TrackingUserLogin;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackingUserRepository extends JpaRepository<TrackingUserLogin, Long> {
    @Modifying
    @Transactional
    void deleteByUserId(Long userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM TrackingUserLogin")
    void deleteAllTrackingUsers();
}
