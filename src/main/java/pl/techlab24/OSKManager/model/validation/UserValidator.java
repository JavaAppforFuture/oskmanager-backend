package pl.techlab24.OSKManager.model.validation;

import pl.techlab24.OSKManager.model.User;
import pl.techlab24.OSKManager.model.enums.Sex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserValidator extends Validator {

    public static List<String> validate(User user) {
        if (user == null) {
            return Collections.singletonList("User cannot be null");
        }

        List<String> result = new ArrayList<>();

        addResultOfValidation(result, validateEmail(user.getEmail()));
        addResultOfValidation(result, validatePassword(user.getPassword()));
        addResultOfValidation(result, validateName(user.getName()));
        addResultOfValidation(result, validateSecondName(user.getSecondName()));
        addResultOfValidation(result, validateSurName(user.getSurname()));
        addResultOfValidation(result, validateSex(user.getSex()));
        addResultOfValidation(result, validatePhoneNumber(user.getPhoneNumber()));

        return result;
    }

    private static String validateEmail(String email) {
        if (email == null) {
            return "Email address cannot be null";
        }

        //TODO: add validation to check email address pattern

        return null;
    }

    private static String validatePassword(String password) {
        if (password == null) {
            return "Password cannot be null";
        }

        //this is just an example, password may have other min. length
        if (password.trim().length() < 8) {
            return "Password must contain at least 8 characters";
        }

        return null;
    }

    private static String validateName(String name) {
        if (name == null) {
            return "Name cannot be null";
        }

        return null;
    }

    private static String validateSecondName(String secondName) {
        if (secondName == null) {
            return "Second name cannot be null";
        }

        return null;
    }

    private static String validateSurName(String surName) {
        if (surName == null) {
            return "Surname name cannot be null";
        }

        return null;
    }

    private static String validateSex(Sex sex) {
        if (sex == null) {
            return "Sex cannot be null";
        }

        return null;
    }

    private static String validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return "Phone number cannot be null";
        }

        //TODO: add validation to check phone number pattern

        return null;
    }

}
