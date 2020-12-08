package pl.techlab24.OSKManager.model.validation;

import pl.techlab24.OSKManager.model.Client;
import pl.techlab24.OSKManager.model.enums.DocumentType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientValidator extends Validator {

    List<String> inputValues;

    public static List<String> validate(Client client) {
        if (client == null) {
            return Collections.singletonList("Client cannot be null.");
        }

        List<String> result = new ArrayList<>();

        addResultOfValidation(result, validateStreet(client.getStreet()));
        addResultOfValidation(result, validateHouseNumber(client.getHouseNumber()));
        addResultOfValidation(result, validateApartmentNumber(client.getApartmentNumber()));
        addResultOfValidation(result, validatePostCode(client.getPostcode()));
        addResultOfValidation(result, validateCity(client.getCity()));
        addResultOfValidation(result, validatePesel(client.getPesel()));
        addResultOfValidation(result, validateDocumentType(client.getDocumentType()));
        addResultOfValidation(result, validateDocumentNumber(client.getDocumentNumber()));

        return result;
    }

    private static String validateStreet(String street) {
        if (street == null) {
            return "Street cannot be null";
        }
        if (street.trim().isEmpty()) {
            return "Street must contain at least 1 character";
        }
        return null;
    }

    private static String validateHouseNumber(String houseNumber) {
        if (houseNumber == null) {
            return "House number cannot be null";
        }

        if (houseNumber.trim().isEmpty()) {
            return "House number must contain at least 1 character";
        }
        return null;
    }

    private static String validateApartmentNumber(String apartmentNumber) {
        if (apartmentNumber == null) {
            return "Apartment number cannot be null";
        }

        if (apartmentNumber.trim().isEmpty()) {
            return "Apartment number must contain at least 1 character";
        }
        return null;
    }

    private static String validatePostCode(String postcode) {
        if (postcode == null) {
            return "Post code number cannot be null";
        }

        if (postcode.trim().isEmpty()) {
            return "Post code number must contain at least 1 character";
        }
        return null;
    }

    private static String validateCity(String city) {
        if (city == null) {
            return "City name cannot be null";
        }

        if (city.trim().isEmpty()) {
            return "Name of city must contain at least 1 character";
        }
        return null;
    }

    private static String validatePesel(String pesel) {
        if (pesel.trim().length() == 11) {
            return null;
        }
        return "Pesel number's length must be 11 characters";
    }

    private static String validateDocumentType(DocumentType documentType) {
        if (documentType == null) {
            return "Document type cannot be null";
        }

        return null;
    }

    private static String validateDocumentNumber(String documentNumber) {
        if (documentNumber == null) {
            return "Document number cannot be null";
        }
        if (documentNumber.trim().isEmpty()) {
            return "Document number must contain at least 1 character";
        }

        return null;
    }

}
