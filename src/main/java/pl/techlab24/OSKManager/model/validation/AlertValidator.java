package pl.techlab24.OSKManager.model.validation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.techlab24.OSKManager.model.Alert;
import pl.techlab24.OSKManager.model.enums.AlertPriority;
import pl.techlab24.OSKManager.model.enums.AlertType;

public class AlertValidator extends Validator {

    public static List<String> validate(Alert alert) {
        if (alert == null) {
            return Collections.singletonList("Alert cannot be null.");
        }

        List<String> result = new ArrayList<>();

        addResultOfValidation(result, validateTitle(alert.getTitle()));
        addResultOfValidation(result, validateDate(alert.getDate()));
        addResultOfValidation(result, validateAlertType(alert.getAlertType()));
        addResultOfValidation(result, validateAlertPriority(alert.getAlertPriority()));

        return result;
    }

    private static String validateTitle(String title) {
        if (title == null) {
            return "Title cannot be null.";
        }
        if (title.trim().isEmpty()) {
            return "Title must contain at least 1 character.";
        }
        return null;
    }

    private static String validateDate(LocalDate date) {
        if (date == null) {
            return "Date cannot be null.";
        }
        return null;
    }

    private static String validateAlertType(AlertType alertType) {
        if (alertType == null) {
            return "Alert type cannot be null.";
        }
        return null;
    }

    private static String validateAlertPriority(AlertPriority alertPriority) {
        if (alertPriority == null) {
            return "Alert priority cannot be null.";
        }
        return null;
    }
}
