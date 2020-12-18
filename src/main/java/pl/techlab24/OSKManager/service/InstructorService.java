package pl.techlab24.OSKManager.service;

import org.springframework.stereotype.Service;
import pl.techlab24.OSKManager.repository.InstructorRepository;

@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;

    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }
}
