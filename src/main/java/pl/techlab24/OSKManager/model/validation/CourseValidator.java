package pl.techlab24.OSKManager.model.validation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.techlab24.OSKManager.model.Category;
import pl.techlab24.OSKManager.model.Course;

public class CourseValidator extends Validator {

    public static List<String> validate(Course course) {

        if (course == null) {
            return Collections.singletonList("Course cannot be null.");
        }

        List<String> result = new ArrayList<>();

        addResultOfValidation(result, validateCourseNumber(course.getCourseNumber()));
        addResultOfValidation(result, validateStartDate(course.getStartDate()));
        addResultOfValidation(result, validateCategory(course.getCategory()));
        addResultOfValidation(result, validateDefaultPrice(course.getDefaultPrice()));

        return result;
    }

    private static String validateCourseNumber(String courseNumber) {
        if (courseNumber == null) {
            return "Course number cannot be null.";
        }
        if (courseNumber.trim().isEmpty()) {
            return "Course number must contain at least 1 character.";
        }
        return null;
    }

    private static String validateStartDate(LocalDate startDate) {
        if (startDate == null) {
            return "Course start date cannot be null.";
        }
        return null;
    }

    private static List<String> validateCategory(Category category) {
        return CategoryValidator.validate(category);
    }

    private static String validateDefaultPrice(BigDecimal defaultPrice) {
        if (defaultPrice == null) {
            return "Default price cannot be null.";
        }
        if (defaultPrice.compareTo(BigDecimal.ZERO) < 0) {
            return "Default price cannot be lower than 0.";
        }
        return null;
    }
}
