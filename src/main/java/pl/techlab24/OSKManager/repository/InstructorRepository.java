package pl.techlab24.OSKManager.repository;

import org.springframework.stereotype.Repository;
import pl.techlab24.OSKManager.model.Instructor;

@Repository
public interface InstructorRepository extends UserBaseRepository<Instructor> {
}
