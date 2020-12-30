package pl.techlab24.OSKManager.model.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.techlab24.OSKManager.model.Office;
import pl.techlab24.OSKManager.model.User;

public class OfficeValidator extends Validator {
    public static List<String> validate(Office office) {
        if (office == null) {
            return Collections.singletonList("Office cannot be null.");
        }

        List<String> result = new ArrayList<>();

        addResultOfValidation(result, validateUser(office));

        return result;
    }

    private static List<String> validateUser(User user) {
        return UserValidator.validate(user);
    }
}
