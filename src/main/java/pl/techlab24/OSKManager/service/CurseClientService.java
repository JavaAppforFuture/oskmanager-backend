package pl.techlab24.OSKManager.service;

import org.springframework.stereotype.Service;
import pl.techlab24.OSKManager.repository.CourseClientRepository;

@Service
public class CurseClientService {

    private final CourseClientRepository courseClientRepository;

    public CurseClientService(CourseClientRepository courseClientRepository) {
        this.courseClientRepository = courseClientRepository;
    }
}
