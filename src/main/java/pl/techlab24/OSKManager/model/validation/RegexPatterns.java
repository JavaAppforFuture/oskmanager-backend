package pl.techlab24.OSKManager.model.validation;

import java.util.regex.Pattern;

public class RegexPatterns {

    private static final Pattern emailPattern =
            Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");

    private static final Pattern mobilePhonePattern =
            Pattern.compile("(\\+48|48)?[4-8][0-9]{8}");

    private static final Pattern landLinePhoneNumberPattern =
            Pattern.compile("(\\+48|48)?([1-9][1-9])?[1-9][0-9]{6}");

    private static final Pattern peselNumberPattern =
            Pattern.compile("[0-9]{4}[0-3]{1}[0-9]{1}[0-9]{5}");

    private static final Pattern dateOfBirthPattern =
            Pattern.compile("^([0-2][0-9]|(3)[0-1])(\\-)(((0)[0-9])|((1)[0-2]))(\\-)\\d{4}$");

    static boolean emailPatternCheck(String email) {
        return emailPattern.matcher(email).matches();
    }

    static boolean phonePatternCheck(String phoneNumber) {
        String inputPhoneNumber = phoneNumber.replaceAll(" ", "");
        return mobilePhonePattern.matcher(inputPhoneNumber).matches()
                || landLinePhoneNumberPattern.matcher(inputPhoneNumber).matches();
    }

    static boolean peselPatternCheck(String pesel) {
        return peselNumberPattern.matcher(pesel).matches()
                || dateOfBirthPattern.matcher(pesel).matches();
    }
}
