package pl.techlab24.OSKManager.service;

import org.springframework.stereotype.Service;
import pl.techlab24.OSKManager.repository.OfficeRepository;

@Service
public class OfficeService {

    private final OfficeRepository officeRepository;

    public OfficeService(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }
}
