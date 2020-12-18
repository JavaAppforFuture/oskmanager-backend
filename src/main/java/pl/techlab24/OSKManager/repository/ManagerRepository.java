package pl.techlab24.OSKManager.repository;

import org.springframework.stereotype.Repository;
import pl.techlab24.OSKManager.model.Manager;

@Repository
public interface ManagerRepository extends UserBaseRepository<Manager> {
}
