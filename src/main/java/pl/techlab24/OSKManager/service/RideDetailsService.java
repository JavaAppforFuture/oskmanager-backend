package pl.techlab24.OSKManager.service;

import org.springframework.stereotype.Service;
import pl.techlab24.OSKManager.repository.RideDetailsRepository;

@Service
public class RideDetailsService {

    private final RideDetailsRepository rideDetailsRepository;

    public RideDetailsService(RideDetailsRepository rideDetailsRepository) {
        this.rideDetailsRepository = rideDetailsRepository;
    }
}
