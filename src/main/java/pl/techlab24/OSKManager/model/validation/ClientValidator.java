package pl.techlab24.OSKManager.model.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        addResultOfValidation(result, validateApartmentNumber(client.getApartmentNumber()));
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

    private static String validateApartmentNumber(String apartmentNumber) {
        if (apartmentNumber == null) {
            return "Apartment number cannot be null.";
        }
        if (apartmentNumber.trim().isEmpty()) {
            return "Apartment number must contain at least 1 character.";
        }
        return null;
    }

    private static String validatePostcode(String postcode) {
        if (postcode == null) {
            return "Postcode number cannot be null.";
        }
        if (postcode.trim().isEmpty()) {
            return "Postcode number must contain at least 1 character.";
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
        if (!RegexPatterns.peselPatternCheck(pesel)) {
            return "Pesel number does not match correct pesel pattern.";
        }
        if (!checkSum(pesel)) {
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

    private static boolean checkSum(String pesel) {
        int lastDigit;
        int[] peselNumbers =  new int[11];

        for (int i = 0; i < pesel.length(); i++) {
            peselNumbers[i] = Integer.parseInt(String.valueOf(pesel.charAt(i)));
        }

        int sum = peselNumbers[0] +
            peselNumbers[1] * 3 +
            peselNumbers[2] * 7 +
            peselNumbers[3] * 9 +
            peselNumbers[4] +
            peselNumbers[5] * 3 +
            peselNumbers[6] * 7 +
            peselNumbers[7] * 9 +
            peselNumbers[8] +
            peselNumbers[9] * 3;

        if ((sum %=10) == 0) {
            lastDigit = 0;
        } else {
            lastDigit = 10 - (sum%10);
        }

        return lastDigit == peselNumbers[10];
    }
}
