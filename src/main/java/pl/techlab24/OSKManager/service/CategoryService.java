package pl.techlab24.OSKManager.service;

import org.springframework.stereotype.Service;
import pl.techlab24.OSKManager.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
