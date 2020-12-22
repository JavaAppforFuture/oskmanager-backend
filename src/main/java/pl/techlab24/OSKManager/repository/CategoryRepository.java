package pl.techlab24.OSKManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.techlab24.OSKManager.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
