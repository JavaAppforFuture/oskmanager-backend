package pl.techlab24.OSKManager.model.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.techlab24.OSKManager.model.Category;

public class CategoryValidator extends Validator {

    public static List<String> validate(Category category) {
        if (category == null) {
            return Collections.singletonList("Category cannot be null.");
        }

        List<String> result = new ArrayList<>();

        addResultOfValidation(result, validateCategoryName(category.getCategoryName()));

        return result;
    }

    private static String validateCategoryName(String categoryName) {
        if (categoryName == null) {
            return "Category name cannot be null.";
        }
        if (categoryName.trim().isEmpty()) {
            return "Category name must contain at least 1 character.";
        }
        return null;
    }
}
