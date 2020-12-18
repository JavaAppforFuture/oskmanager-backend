package pl.techlab24.OSKManager.service;

import org.springframework.stereotype.Service;
import pl.techlab24.OSKManager.repository.CourseRepository;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
}
