package pl.techlab24.OSKManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.techlab24.OSKManager.model.CourseClient;
import pl.techlab24.OSKManager.model.CourseClientId;

@Repository
public interface CourseClientRepository extends JpaRepository<CourseClient, CourseClientId> {
}
