package pl.techlab24.OSKManager.model.validation;

import java.util.regex.Pattern;

public class RegexPatterns {

    private static final Pattern emailPattern =
            Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");

    private static final Pattern phonePattern =
            Pattern.compile("(\\+48|48)?[4-8][0-9]{8}");

    static boolean emailPatternCheck(String email) {
        return emailPattern.matcher(email).matches();
    }

    static boolean phonePatternCheck(String phoneNumber) {
        return phonePattern.matcher(phoneNumber).matches();
    }

}
