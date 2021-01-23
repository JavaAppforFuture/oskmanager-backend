package pl.techlab24.OSKManager.model.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.techlab24.OSKManager.model.Manager;
import pl.techlab24.OSKManager.model.User;

public class ManagerValidator extends Validator {

    public static List<String> validate(Manager manager) {
        if (manager == null) {
            return Collections.singletonList("Manager cannot be null.");
        }

        List<String> result = new ArrayList<>();

        addResultOfValidation(result, validateUser(manager));

        return result;
    }

    private static List<String> validateUser(User user) {
        return UserValidator.validate(user);
    }
}
