package pl.techlab24.OSKManager.model.validation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.techlab24.OSKManager.model.Category;
import pl.techlab24.OSKManager.model.Instructor;

public class InstructorValidator extends Validator {

    public static List<String> validate(Instructor instructor) {
        if (instructor == null) {
            return Collections.singletonList("Instructor cannot be null.");
        }

        List<String> result = new ArrayList<>();

        for (Category category : instructor.getCategories()) {
            addResultOfValidation(result, validateCategory(category));
        }

        addResultOfValidation(result, UserValidator.validate(instructor));
        addResultOfValidation(result, validateStandardPaymentRate(instructor.getStandardPaymentRate()));
        addResultOfValidation(result, validateAdditionalPaymentRate(instructor.getAdditionalPaymentRate()));
        addResultOfValidation(result, validateInstructorNumber(instructor.getInstructorNumber()));
        addResultOfValidation(result, validateLicenceExpireDate(instructor.getLicenceExpireDate()));

        return result;
    }

    private static List<String> validateCategory(Category category) {
        return CategoryValidator.validate(category);
    }

    private static String validateStandardPaymentRate(BigDecimal standardPaymentRate) {
        if (standardPaymentRate == null) {
            return "Standard payment rate cannot be null.";
        }
        if (standardPaymentRate.compareTo(BigDecimal.ZERO) < 0) {
            return "Standard payment rate cannot be lower than 0.";
        }
        return null;
    }

    private static String validateAdditionalPaymentRate(BigDecimal additionalPaymentRate) {
        if (additionalPaymentRate == null) {
            return "Additional payment rate cannot be null.";
        }
        if (additionalPaymentRate.compareTo(BigDecimal.ZERO) < 0) {
            return "Additional payment rate cannot be lower than 0.";
        }
        return null;
    }

    private static String validateInstructorNumber(String instructorNumber) {
        if (instructorNumber == null) {
            return "Instructor number cannot be null.";
        }
        if (instructorNumber.trim().isEmpty()) {
            return "Instructor number must contain at least 1 character.";
        }
        return null;
    }

    private static String validateLicenceExpireDate(LocalDate licenceExpireDate) {
        if (licenceExpireDate == null) {
            return "Licence expire date cannot be null.";
        }
        return null;
    }
}
