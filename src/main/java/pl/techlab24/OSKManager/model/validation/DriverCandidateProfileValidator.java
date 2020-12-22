package pl.techlab24.OSKManager.model.validation;

import pl.techlab24.OSKManager.model.DriverCandidateProfile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DriverCandidateProfileValidator extends Validator {

    public static List<String> validate(DriverCandidateProfile driverCandidateProfile) {
        if (driverCandidateProfile == null) {
            return Collections.singletonList("Driver candidate profile cannot be null.");
        }

        List<String> result = new ArrayList<>();

        addResultOfValidation(result, validateNumber(driverCandidateProfile.getNumber()));
        addResultOfValidation(result, CategoryValidator.validate(driverCandidateProfile.getCategory()));
        addResultOfValidation(result, validateAddedDate(driverCandidateProfile.getAddedDate()));

        return result;
    }

    private static String validateNumber(String number) {
        if (number == null) {
            return "Driver candidate profile number cannot be null.";
        }

        //TODO: check this variable for correct number length;
        if (number.trim().isEmpty()) {
            return "Driver candidate profile must contain at least 1.";
        }

        return null;
    }

    private static String validateAddedDate(LocalDate date) {
        if (date == null) {
            return "The date the profile was added cannot be null.";
        }

        return null;
    }

}
