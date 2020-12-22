package pl.techlab24.OSKManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.techlab24.OSKManager.model.Ride;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
}
