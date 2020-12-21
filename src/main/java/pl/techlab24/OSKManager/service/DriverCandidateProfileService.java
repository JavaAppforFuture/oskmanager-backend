package pl.techlab24.OSKManager.service;

import org.springframework.stereotype.Service;
import pl.techlab24.OSKManager.repository.DriverCandidateProfileRepository;

@Service
public class DriverCandidateProfileService {

    private final DriverCandidateProfileRepository driverCandidateProfileRepository;

    public DriverCandidateProfileService(DriverCandidateProfileRepository driverCandidateProfileRepository) {
        this.driverCandidateProfileRepository = driverCandidateProfileRepository;
    }
}
