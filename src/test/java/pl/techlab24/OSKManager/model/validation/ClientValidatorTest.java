package pl.techlab24.OSKManager.model.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import pl.techlab24.OSKManager.model.Client;
import pl.techlab24.OSKManager.model.enums.DocumentType;
import pl.techlab24.OSKManager.model.enums.Sex;

class ClientValidatorTest {

    private Client correctClient;

    @BeforeEach
    void setup() {
        correctClient = Client.builder()
            .id(1L)
            .email("test@test.com")
            .password("test_password_*#Eghicnshd765()")
            .name("Test Name")
            .secondName("Test Second Name")
            .surname("Test Surname")
            .sex(Sex.Other)
            .phoneNumber("+48123456789")
            .defaultPassword(null)
            .role("Test Role")
            .street("Test Street")
            .houseNumber("1")
            .apartmentNumber(null)
            .postcode("00-000")
            .city("Test City")
            .pesel("10.10.2000")
            .documentType(DocumentType.PhotoId)
            .documentNumber("012345")
            .build();
    }

    @Test
    void shouldValidateCorrectClient() {
        // when
        List<String> resultOfValidation = ClientValidator.validate(correctClient);

        // then
        assertEquals(Collections.emptyList(), resultOfValidation);
    }

    @Test
    void shouldValidateNullClient() {
        // when
        List<String> resultOfValidation = ClientValidator.validate(null);

        // then
        assertEquals(Collections.singletonList("Client cannot be null."), resultOfValidation);
    }

    @Test
    void shouldValidateUserMethodCallValidateMethodFromUserValidatorClass() {
        try (MockedStatic<UserValidator> mockedStatic = mockStatic(UserValidator.class)) {
            // given
            mockedStatic.when(() -> UserValidator.validate(correctClient)).thenReturn(Collections.emptyList());

            // when
            List<String> result = ClientValidator.validate(correctClient);

            // then
            assertEquals(Collections.emptyList(), result);
            mockedStatic.verify(() -> UserValidator.validate(correctClient));
        }
    }

    @ParameterizedTest
    @MethodSource("setOfStreetsAndValidationResults")
    void shouldValidateStreet(String street, List<String> expected) {
        // given
        Client clientWithVariableStreet = correctClient.toBuilder().street(street).build();

        // when
        List<String> resultOfValidation = ClientValidator.validate(clientWithVariableStreet);

        // then
        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfStreetsAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("Street cannot be null.")),
            Arguments.of("", Collections.singletonList("Street must contain at least 1 character.")),
            Arguments.of("   ", Collections.singletonList("Street must contain at least 1 character.")),
            Arguments.of("Main", Collections.emptyList()),
            Arguments.of("A", Collections.emptyList())
        );
    }

    @ParameterizedTest
    @MethodSource("setOfHouseNumbersAndValidationResults")
    void shouldValidateHouseNumber(String houseNumber, List<String> expected) {
        // given
        Client clientWithVariableHouseNumber = correctClient.toBuilder().houseNumber(houseNumber).build();

        // when
        List<String> resultOfValidation = ClientValidator.validate(clientWithVariableHouseNumber);

        // then
        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfHouseNumbersAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("House number cannot be null.")),
            Arguments.of("", Collections.singletonList("House number must contain at least 1 character.")),
            Arguments.of("   ", Collections.singletonList("House number must contain at least 1 character.")),
            Arguments.of("112", Collections.emptyList()),
            Arguments.of("4a", Collections.emptyList()),
            Arguments.of("39/41", Collections.emptyList())
        );
    }

    @ParameterizedTest
    @MethodSource("setOfPostcodesAndValidationResults")
    void shouldValidatePostcode(String postcode, List<String> expected) {
        // given
        Client clientWithVariablePostcode = correctClient.toBuilder().postcode(postcode).build();

        // when
        List<String> resultOfValidation = ClientValidator.validate(clientWithVariablePostcode);

        // then
        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfPostcodesAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("Postcode cannot be null.")),
            Arguments.of("", Collections.singletonList("Postcode must contain at least 1 character.")),
            Arguments.of("   ", Collections.singletonList("Postcode must contain at least 1 character.")),
            Arguments.of("00-000", Collections.emptyList()),
            Arguments.of("17-201", Collections.emptyList()),
            Arguments.of("99-324", Collections.emptyList())
        );
    }

    @ParameterizedTest
    @MethodSource("setOfCitiesAndValidationResults")
    void shouldValidateCity(String city, List<String> expected) {
        // given
        Client clientWithVariableCity = correctClient.toBuilder().city(city).build();

        // when
        List<String> resultOfValidation = ClientValidator.validate(clientWithVariableCity);

        // then
        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfCitiesAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("Name of city cannot be null.")),
            Arguments.of("", Collections.singletonList("Name of city must contain at least 1 character.")),
            Arguments.of("   ", Collections.singletonList("Name of city must contain at least 1 character.")),
            Arguments.of("Warsaw", Collections.emptyList()),
            Arguments.of("A", Collections.emptyList()),
            Arguments.of("Gdansk", Collections.emptyList())
        );
    }

    @ParameterizedTest
    @MethodSource("setOfPeselsAndValidationResults")
    void shouldValidatePesel(String pesel, List<String> expected) {
        // given
        Client clientWithVariablePesel = correctClient.toBuilder().pesel(pesel).build();

        // when
        List<String> resultOfValidation = ClientValidator.validate(clientWithVariablePesel);

        // then
        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfPeselsAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("Pesel number cannot be null.")),
            Arguments.of("", Collections.singletonList("Pesel number does not match correct pesel pattern.")),
            Arguments.of("   ", Collections.singletonList("Pesel number does not match correct pesel pattern.")),
            Arguments.of("72011517224*", Collections.singletonList("Pesel number does not match correct pesel pattern.")),
            Arguments.of("72011517224-", Collections.singletonList("Pesel number does not match correct pesel pattern.")),
            Arguments.of("X72011517224", Collections.singletonList("Pesel number does not match correct pesel pattern.")),
            Arguments.of("11 12 2000", Collections.singletonList("Pesel number does not match correct pesel pattern.")),
            Arguments.of("12/12/2012", Collections.singletonList("Pesel number does not match correct pesel pattern.")),
            Arguments.of("12.13.2000", Collections.singletonList("Pesel number does not match correct pesel pattern.")),
            Arguments.of("40.01.2000", Collections.singletonList("Pesel number does not match correct pesel pattern.")),
            Arguments.of("62011517224", Collections.singletonList("Pesel number is incorrect.")),
            Arguments.of("70020509530", Collections.singletonList("Pesel number is incorrect.")),
            Arguments.of("82011617221", Collections.singletonList("Pesel number is incorrect.")),
            Arguments.of("82011617220", Collections.emptyList()),
            Arguments.of("72011517224", Collections.emptyList()),
            Arguments.of("79070339378", Collections.emptyList()),
            Arguments.of("04231298227", Collections.emptyList()),
            Arguments.of("01-12-1999", Collections.emptyList()),
            Arguments.of("01.12.1999", Collections.emptyList())
        );
    }

    @Test
    void shouldValidateNullDocumentType() {
        // given
        Client clientWithNullDocumentType = correctClient.toBuilder().documentType(null).build();

        // when
        List<String> resultOfValidation = ClientValidator.validate(clientWithNullDocumentType);

        // then
        assertEquals(Collections.singletonList("Document type cannot be null."), resultOfValidation);
    }

    @ParameterizedTest
    @MethodSource("setOfDocumentNumbersAndValidationResults")
    void shouldValidateDocumentNumber(String documentNumber, List<String> expected) {
        // given
        Client clientWithVariableDocumentNumber = correctClient.toBuilder().documentNumber(documentNumber).build();

        // when
        List<String> resultOfValidation = ClientValidator.validate(clientWithVariableDocumentNumber);

        // then
        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfDocumentNumbersAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("Document number cannot be null.")),
            Arguments.of("", Collections.singletonList("Document number must contain at least 1 character.")),
            Arguments.of("   ", Collections.singletonList("Document number must contain at least 1 character.")),
            Arguments.of("AB012345", Collections.emptyList()),
            Arguments.of("XX27-345", Collections.emptyList()),
            Arguments.of("12BXA -37C", Collections.emptyList())
        );
    }
}
