package pl.techlab24.OSKManager.model.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.techlab24.OSKManager.model.Category;
import pl.techlab24.OSKManager.model.Course;
import pl.techlab24.OSKManager.model.DriverCandidateProfile;
import pl.techlab24.OSKManager.model.Instructor;

public class CategoryValidator extends Validator {

    public static List<String> validate(Category category) {
        if (category == null) {
            return Collections.singletonList("Category cannot be null.");
        }

        List<String> result = new ArrayList<>();

        addResultOfValidation(result, validateCategoryName(category.getCategoryName()));
        addResultOfValidation(result, validateCourse(category.getCourse()));
        addResultOfValidation(result, validateDriverCandidateProfile(category.getDriverCandidateProfile()));
        addResultOfValidation(result, validateInstructor(category.getInstructor()));

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

    private static String validateCourse(Course course) {
        if (course == null) {
            return "Course cannot be null";
        }

        return null;
    }

    private static String validateDriverCandidateProfile(DriverCandidateProfile candidateProfile) {
        if (candidateProfile == null) {
            return "Driver candidate profile cannot be null";
        }

        return null;

    }

    private static String validateInstructor(Instructor instructor) {
        if (instructor == null) {
            return "Instructor cannot be null";
        }

        return null;
    }
}
