package pl.techlab24.OSKManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import pl.techlab24.OSKManager.model.User;

@NoRepositoryBean
public interface UserBaseRepository<T extends User> extends JpaRepository<User, Long> {
}
