package group5.SE1863.DPSS_backend.repository;

import group5.SE1863.DPSS_backend.entity.InvalidatedToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {
    @Modifying
    @Transactional
    @Query("DELETE FROM InvalidatedToken t WHERE t.timeExpired < :currentTime")
    void deleteExpiredTokens(Date currentTime);
}
