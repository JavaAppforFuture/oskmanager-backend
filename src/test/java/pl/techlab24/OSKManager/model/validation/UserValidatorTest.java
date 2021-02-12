package pl.techlab24.OSKManager.model.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import pl.techlab24.OSKManager.model.User;
import pl.techlab24.OSKManager.model.enums.Sex;

class UserValidatorTest {

    private User correctUser;

    @BeforeEach
    void setup() {
        correctUser = Mockito.mock(
            User.class,
            Mockito.CALLS_REAL_METHODS);
        correctUser.setId(1L);
        correctUser.setEmail("test@test.com");
        correctUser.setPassword("test_password_957*$");
        correctUser.setName("Test Name");
        correctUser.setSecondName("Test Second Name");
        correctUser.setSurname("Test Surname");
        correctUser.setSex(Sex.Other);
        correctUser.setPhoneNumber("+48500112233");
        correctUser.setDefaultPassword(null);
        correctUser.setRole("Test Role");
    }

    @Test
    void shouldValidateCorrectUser() {
        // when
        List<String> resultOfValidation = UserValidator.validate(correctUser);

        // then
        assertEquals(Collections.emptyList(), resultOfValidation);
    }

    @Test
    void shouldValidateNullUser() {
        // when
        List<String> resultOfValidation = UserValidator.validate(null);

        // then
        assertEquals(Collections.singletonList("User cannot be null."), resultOfValidation);
    }

    @ParameterizedTest
    @MethodSource("setOfEmailsAndValidationResults")
    void shouldValidateEmail(String email, List<String> expected) {
        // given
        User userWithVariableEmail = correctUser;
        userWithVariableEmail.setEmail(email);

        // when
        List<String> resultOfValidation = UserValidator.validate(userWithVariableEmail);

        // then
        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfEmailsAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("Email address cannot be null.")),
            Arguments.of("", Collections.singletonList("Email does not match correct email pattern.")),
            Arguments.of("   ", Collections.singletonList("Email does not match correct email pattern.")),
            Arguments.of("Test email@123.com", Collections.singletonList("Email does not match correct email pattern.")),
            Arguments.of("***@123.com", Collections.singletonList("Email does not match correct email pattern.")),
            Arguments.of("test@wp pl", Collections.singletonList("Email does not match correct email pattern.")),
            Arguments.of("@wp pl", Collections.singletonList("Email does not match correct email pattern.")),
            Arguments.of("t*est@wp pl", Collections.singletonList("Email does not match correct email pattern.")),
            Arguments.of("@wp.pl", Collections.singletonList("Email does not match correct email pattern.")),
            Arguments.of("test@123.com.pl", Collections.emptyList()),
            Arguments.of("12_3@aaa.com.pl", Collections.emptyList()),
            Arguments.of("12_3@a-a-a-a.com.pl", Collections.emptyList()),
            Arguments.of("12_3@a-a-a-a.online", Collections.emptyList()),
            Arguments.of("test@wp.pl", Collections.emptyList())
        );
    }

    @ParameterizedTest
    @MethodSource("setOfPasswordsAndValidationResults")
    void shouldValidatePassword(String password, List<String> expected) {
        // given
        User userWithVariablePassword = correctUser;
        userWithVariablePassword.setPassword(password);

        // when
        List<String> resultOfValidation = UserValidator.validate(userWithVariablePassword);

        // then
        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfPasswordsAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("Password cannot be null.")),
            Arguments.of("", Collections.singletonList("Password must contain at least 8 characters.")),
            Arguments.of("   ", Collections.singletonList("Password must contain at least 8 characters.")),
            Arguments.of("pass", Collections.singletonList("Password must contain at least 8 characters.")),
            Arguments.of("%^&*cd", Collections.singletonList("Password must contain at least 8 characters.")),
            Arguments.of("password", Collections.emptyList()),
            Arguments.of("pass**&9547_aa", Collections.emptyList()),
            Arguments.of("a.s,dv__hu876543wa%^&*(", Collections.emptyList())
        );
    }

    @ParameterizedTest
    @MethodSource("setOfNamesAndValidationResults")
    void shouldValidateName(String name, List<String> expected) {
        // given
        User userWithVariableName = correctUser;
        userWithVariableName.setName(name);

        // when
        List<String> resultOfValidation = UserValidator.validate(userWithVariableName);

        // then
        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfNamesAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("Name cannot be null.")),
            Arguments.of("", Collections.singletonList("Name must contain at least 1 character.")),
            Arguments.of("   ", Collections.singletonList("Name must contain at least 1 character.")),
            Arguments.of("Jan", Collections.emptyList()),
            Arguments.of("A.", Collections.emptyList())
        );
    }

    @ParameterizedTest
    @MethodSource("setOfSecondNamesAndValidationResults")
    void shouldValidateSecondName(String secondName, List<String> expected) {
        // given
        User userWithVariableSecondName = correctUser;
        userWithVariableSecondName.setSecondName(secondName);

        // when
        List<String> resultOfValidation = UserValidator.validate(userWithVariableSecondName);

        // then
        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfSecondNamesAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("Second name cannot be null.")),
            Arguments.of("", Collections.singletonList("Second name must contain at least 1 character.")),
            Arguments.of("   ", Collections.singletonList("Second name must contain at least 1 character.")),
            Arguments.of("Jacek", Collections.emptyList()),
            Arguments.of("X.", Collections.emptyList())
        );
    }

    @ParameterizedTest
    @MethodSource("setOfSurnamesAndValidationResults")
    void shouldValidateSurname(String surname, List<String> expected) {
        // given
        User userWithVariableSurname = correctUser;
        userWithVariableSurname.setSurname(surname);

        // when
        List<String> resultOfValidation = UserValidator.validate(userWithVariableSurname);

        // then
        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfSurnamesAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("Surname cannot be null.")),
            Arguments.of("", Collections.singletonList("Surname must contain at least 1 character.")),
            Arguments.of("   ", Collections.singletonList("Surname must contain at least 1 character.")),
            Arguments.of("Kowalski", Collections.emptyList()),
            Arguments.of("A.", Collections.emptyList())
        );
    }

    @Test
    void shouldValidateNullSex() {
        // given
        User userWithNullSex = correctUser;
        userWithNullSex.setSex(null);

        // when
        List<String> resultOfValidation = UserValidator.validate(userWithNullSex);

        // then
        assertEquals(Collections.singletonList("Sex cannot be null."), resultOfValidation);
    }

    @ParameterizedTest
    @MethodSource("setOfPhoneNumbersAndValidationResults")
    void shouldValidatePhoneNumber(String phoneNumber, List<String> expected) {
        // given
        User userWithVariablePhoneNumber = correctUser;
        userWithVariablePhoneNumber.setPhoneNumber(phoneNumber);

        // when
        List<String> resultOfValidation = UserValidator.validate(userWithVariablePhoneNumber);

        // then
        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfPhoneNumbersAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("Phone number cannot be null.")),
            Arguments.of("", Collections.singletonList("Phone number does not match correct phone number pattern.")),
            Arguments.of("   ", Collections.singletonList("Phone number does not match correct phone number pattern.")),
            Arguments.of("+48-601-11-22-33", Collections.singletonList("Phone number does not match correct phone number pattern.")),
            Arguments.of("+48 601 11 22 33", Collections.emptyList()),
            Arguments.of("+48 12 350 30 30", Collections.emptyList()),
            Arguments.of("+48601112233", Collections.emptyList()),
            Arguments.of("+48123503030", Collections.emptyList())
        );
    }

    @ParameterizedTest
    @MethodSource("setOfRolesAndValidationResults")
    void shouldValidateRole(String role, List<String> expected) {
        // given
        User userWithVariableRole = correctUser;
        userWithVariableRole.setRole(role);

        // when
        List<String> resultOfValidation = UserValidator.validate(userWithVariableRole);

        // then
        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfRolesAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("Role cannot be null.")),
            Arguments.of("", Collections.singletonList("Role must contain at least 1 character.")),
            Arguments.of("   ", Collections.singletonList("Role must contain at least 1 character.")),
            Arguments.of("Admin", Collections.emptyList()),
            Arguments.of("CLIENT", Collections.emptyList()),
            Arguments.of("A.", Collections.emptyList())
        );
    }
}
