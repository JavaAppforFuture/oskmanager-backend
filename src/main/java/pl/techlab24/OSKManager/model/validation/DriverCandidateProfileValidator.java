package pl.techlab24.OSKManager.model.validation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.techlab24.OSKManager.model.Category;
import pl.techlab24.OSKManager.model.DriverCandidateProfile;

public class DriverCandidateProfileValidator extends Validator {

    public static List<String> validate(DriverCandidateProfile driverCandidateProfile) {
        if (driverCandidateProfile == null) {
            return Collections.singletonList("Driver candidate profile cannot be null.");
        }

        List<String> result = new ArrayList<>();

        addResultOfValidation(result, validateNumber(driverCandidateProfile.getNumber()));
        addResultOfValidation(result, validateCategory(driverCandidateProfile.getCategory()));
        addResultOfValidation(result, validateAddedDate(driverCandidateProfile.getAddedDate()));

        return result;
    }

    private static String validateNumber(String number) {
        if (number == null) {
            return "Driver candidate profile number cannot be null.";
        }
        if (number.trim().isEmpty()) {
            return "Driver candidate profile number must contain at least 1 character.";
        }
        return null;
    }

    private static List<String> validateCategory(Category category) {
        return CategoryValidator.validate(category);
    }

    private static String validateAddedDate(LocalDate date) {
        if (date == null) {
            return "The date the profile was added cannot be null.";
        }
        if (date.isAfter(LocalDate.now())) {
            return "The date the profile was added cannot be later than now.";
        }
        return null;
    }
}
