package pl.techlab24.OSKManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.techlab24.OSKManager.model.RideDetails;

@Repository
public interface RideDetailsRepository extends JpaRepository<RideDetails, Long> {
}
