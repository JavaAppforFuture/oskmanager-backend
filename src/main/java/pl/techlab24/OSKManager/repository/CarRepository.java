package pl.techlab24.OSKManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.techlab24.OSKManager.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
