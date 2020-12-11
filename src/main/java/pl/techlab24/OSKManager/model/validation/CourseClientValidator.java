package pl.techlab24.OSKManager.model.validation;

import pl.techlab24.OSKManager.model.CourseClient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CourseClientValidator extends Validator {

    public static List<String> validate(CourseClient courseClient) {
        if (courseClient == null) {
            return Collections.singletonList("Course client cannot be null");
        }

        List<String> result = new ArrayList<>();

        addResultOfValidation(result, validateCustomPrice(courseClient.getCustomPrice()));

        return result;
    }

    private static String validateCustomPrice(BigDecimal customPrice) {
        if (customPrice == null) {
            return "Custom price cannot be null";
        }

        if (customPrice.compareTo(BigDecimal.ZERO) < 0) {
            return "Custom price cannot be lower than 0";
        }

        return null;
    }

}
