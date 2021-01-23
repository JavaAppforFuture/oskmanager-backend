package pl.techlab24.OSKManager.model.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.techlab24.OSKManager.model.User;
import pl.techlab24.OSKManager.model.enums.Sex;

public class UserValidator extends Validator {

    public static List<String> validate(User user) {
        if (user == null) {
            return Collections.singletonList("User cannot be null.");
        }

        List<String> result = new ArrayList<>();

        addResultOfValidation(result, validateEmail(user.getEmail()));
        addResultOfValidation(result, validatePassword(user.getPassword()));
        addResultOfValidation(result, validateName(user.getName()));
        addResultOfValidation(result, validateSecondName(user.getSecondName()));
        addResultOfValidation(result, validateSurname(user.getSurname()));
        addResultOfValidation(result, validateSex(user.getSex()));
        addResultOfValidation(result, validatePhoneNumber(user.getPhoneNumber()));
        addResultOfValidation(result, validateRole(user.getRole()));

        return result;
    }

    private static String validateEmail(String email) {
        if (email == null) {
            return "Email address cannot be null.";
        }
        if (!RegexPatterns.emailPatternCheck(email)) {
            return "Email does not match correct email pattern.";
        }
        return null;
    }

    private static String validatePassword(String password) {
        if (password == null) {
            return "Password cannot be null.";
        }
        if (password.trim().length() < 8) {
            return "Password must contain at least 8 characters.";
        }
        return null;
    }

    private static String validateName(String name) {
        if (name == null) {
            return "Name cannot be null.";
        }
        if (name.trim().isEmpty()) {
            return "Name must contain at least 1 character.";
        }
        return null;
    }

    private static String validateSecondName(String secondName) {
        if (secondName == null) {
            return "Second name cannot be null.";
        }
        if (secondName.trim().isEmpty()) {
            return "Second name must contain at least 1 character.";
        }
        return null;
    }

    private static String validateSurname(String surname) {
        if (surname == null) {
            return "Surname cannot be null.";
        }
        if (surname.trim().isEmpty()) {
            return "Surname must contain at least 1 character.";
        }
        return null;
    }

    private static String validateSex(Sex sex) {
        if (sex == null) {
            return "Sex cannot be null.";
        }
        return null;
    }

    private static String validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return "Phone number cannot be null.";
        }
        if (!RegexPatterns.phonePatternCheck(phoneNumber)) {
            return "Phone number does not match correct phone number pattern.";
        }
        return null;
    }

    private static String validateRole(String role) {
        if (role == null) {
            return "Role cannot be null.";
        }
        if (role.trim().isEmpty()) {
            return "Role must contain at least 1 character.";
        }
        return null;
    }
}
