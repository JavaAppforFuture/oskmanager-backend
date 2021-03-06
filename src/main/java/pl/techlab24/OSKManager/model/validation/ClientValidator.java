package pl.techlab24.OSKManager.model.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.validator.internal.constraintvalidators.hv.pl.PESELValidator;
import pl.techlab24.OSKManager.model.Client;
import pl.techlab24.OSKManager.model.User;
import pl.techlab24.OSKManager.model.enums.DocumentType;

public class ClientValidator extends Validator {

    public static List<String> validate(Client client) {
        if (client == null) {
            return Collections.singletonList("Client cannot be null.");
        }

        List<String> result = new ArrayList<>();

        addResultOfValidation(result, validateUser(client));
        addResultOfValidation(result, validateStreet(client.getStreet()));
        addResultOfValidation(result, validateHouseNumber(client.getHouseNumber()));
        addResultOfValidation(result, validatePostcode(client.getPostcode()));
        addResultOfValidation(result, validateCity(client.getCity()));
        addResultOfValidation(result, validatePesel(client.getPesel()));
        addResultOfValidation(result, validateDocumentType(client.getDocumentType()));
        addResultOfValidation(result, validateDocumentNumber(client.getDocumentNumber()));

        return result;
    }

    private static List<String> validateUser(User user) {
        return UserValidator.validate(user);
    }

    private static String validateStreet(String street) {
        if (street == null) {
            return "Street cannot be null.";
        }
        if (street.trim().isEmpty()) {
            return "Street must contain at least 1 character.";
        }
        return null;
    }

    private static String validateHouseNumber(String houseNumber) {
        if (houseNumber == null) {
            return "House number cannot be null.";
        }
        if (houseNumber.trim().isEmpty()) {
            return "House number must contain at least 1 character.";
        }
        return null;
    }

    private static String validatePostcode(String postcode) {
        if (postcode == null) {
            return "Postcode cannot be null.";
        }
        if (postcode.trim().isEmpty()) {
            return "Postcode must contain at least 1 character.";
        }
        return null;
    }

    private static String validateCity(String city) {
        if (city == null) {
            return "Name of city cannot be null.";
        }
        if (city.trim().isEmpty()) {
            return "Name of city must contain at least 1 character.";
        }
        return null;
    }

    private static String validatePesel(String pesel) {
        if (pesel == null) {
            return "Pesel number cannot be null.";
        }
        if (RegexPatterns.dateOfBirthPatternCheck(pesel)) {
            return null;
        }
        if (!RegexPatterns.peselPatternCheck(pesel)) {
            return "Pesel number does not match correct pesel pattern.";
        }
        if (!isValid(pesel)) {
            return "Pesel number is incorrect.";
        }
        return null;
    }

    private static String validateDocumentType(DocumentType documentType) {
        if (documentType == null) {
            return "Document type cannot be null.";
        }
        return null;
    }

    private static String validateDocumentNumber(String documentNumber) {
        if (documentNumber == null) {
            return "Document number cannot be null.";
        }
        if (documentNumber.trim().isEmpty()) {
            return "Document number must contain at least 1 character.";
        }
        return null;
    }

    private static boolean isValid(String pesel) {
        PESELValidator validator = new PESELValidator();
        validator.initialize(null);

        return validator.isValid(pesel, null);
    }
}
