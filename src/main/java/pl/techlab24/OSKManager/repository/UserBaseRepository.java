package pl.techlab24.OSKManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.techlab24.OSKManager.model.User;

@Repository
public interface UserBaseRepository<T extends User> extends JpaRepository<User, Long> {
}
