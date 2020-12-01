package pl.techlab24.OSKManager.model.validation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.techlab24.OSKManager.model.Car;

public class CarValidator extends Validator {

    public static List<String> validate(Car car) {
        if (car == null) {
            return Collections.singletonList("Car cannot be null.");
        }

        List<String> result = new ArrayList<>();

        addResultOfValidation(result, validateMark(car.getMark()));
        addResultOfValidation(result, validateModel(car.getModel()));
        addResultOfValidation(result, validatePlate(car.getPlate()));
        addResultOfValidation(result, validateProductionYear(car.getProductionYear()));
        addResultOfValidation(result, validateEndOfReviewDate(car.getEndOfReview()));
        addResultOfValidation(result, validateEndOfInsuranceDate(car.getEndOfInsurance()));

        return result;
    }

    private static String validateMark(String mark) {
        if (mark == null) {
            return "Mark cannot be null.";
        }
        if (mark.trim().isEmpty()) {
            return "Mark must contain at least 1 character.";
        }
        return null;
    }

    private static String validateModel(String model) {
        if (model == null) {
            return "Model cannot be null.";
        }
        if (model.trim().isEmpty()) {
            return "Model must contain at least 1 character.";
        }
        return null;
    }

    private static String validatePlate(String plate) {
        if (plate == null) {
            return "Plate cannot be null.";
        }
        if (plate.trim().isEmpty()) {
            return "Plate must contain at least 1 character.";
        }
        return null;
    }

    private static String validateProductionYear(Integer productionYear) {
        if (productionYear == null) {
            return "Production year cannot be null.";
        }
        if (productionYear < 1900 || productionYear > (Integer) LocalDate.now().getYear()) {
            return "Production year cannot be lower than 1900 or greater than current year.";
        }
        return null;
    }

    private static String validateEndOfReviewDate(LocalDate endOfReviewDate) {
        if (endOfReviewDate == null) {
            return "End of review date cannot be null.";
        }
        return null;
    }

    private static String validateEndOfInsuranceDate(LocalDate endOfInsuranceDate) {
        if (endOfInsuranceDate == null) {
            return "End of insurance date cannot be null.";
        }
        return null;
    }
}
