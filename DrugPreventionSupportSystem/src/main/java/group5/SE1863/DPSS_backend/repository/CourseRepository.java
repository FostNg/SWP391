package group5.SE1863.DPSS_backend.repository;

import group5.SE1863.DPSS_backend.entity.Courses;
import group5.SE1863.DPSS_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Courses, Long> {
    // Additional query methods can be defined here if needed
    List<Courses> findByEnrolledUsers(User user);
}
