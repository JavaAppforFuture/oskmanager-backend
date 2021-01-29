package pl.techlab24.OSKManager.model.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import pl.techlab24.OSKManager.model.Category;
import pl.techlab24.OSKManager.model.Instructor;
import pl.techlab24.OSKManager.model.enums.Sex;

class InstructorValidatorTest {

    private Instructor correctInstructor;

    @BeforeEach
    void setup() {
        Category categoryA = new Category(1L,
            "A",
            null,
            null,
            null);

        Category categoryB = new Category(1L,
            "B",
            null,
            null,
            null);

        correctInstructor = Instructor.builder()
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
            .categories(new ArrayList<>(Arrays.asList(categoryA, categoryB)))
            .standardPaymentRate(BigDecimal.valueOf(100.00))
            .additionalPaymentRate(BigDecimal.valueOf(200.00))
            .instructorNumber("I-024")
            .licenceExpireDate(LocalDate.now().plusYears(1L))
            .build();
    }

    @Test
    void shouldValidateCorrectInstructor() {
        List<String> resultOfValidation = InstructorValidator.validate(correctInstructor);
        assertEquals(Collections.emptyList(), resultOfValidation);
    }

    @Test
    void shouldValidateNullInstructor() {
        List<String> resultOfValidation = InstructorValidator.validate(null);
        assertEquals(Collections.singletonList("Instructor cannot be null."), resultOfValidation);
    }

    @Test
    void shouldValidateCategoryMethodCallValidateMethodFromCategoryValidatorClass() {
        try (MockedStatic<CategoryValidator> mockedStatic = mockStatic(CategoryValidator.class)) {
            // given
            mockedStatic.when(() -> CategoryValidator.validate(any())).thenReturn(Collections.emptyList());

            // when
            List<String> result = InstructorValidator.validate(correctInstructor);

            // then
            assertEquals(Collections.emptyList(), result);
            mockedStatic.verify(times(correctInstructor.getCategories().size()), () -> CategoryValidator.validate(any()));
        }
    }

    @Test
    void shouldValidateUserMethodCallValidateMethodFromUserValidatorClass() {
        try (MockedStatic<UserValidator> mockedStatic = mockStatic(UserValidator.class)) {
            // given
            mockedStatic.when(() -> UserValidator.validate(correctInstructor)).thenReturn(Collections.emptyList());

            // when
            List<String> result = InstructorValidator.validate(correctInstructor);

            // then
            assertEquals(Collections.emptyList(), result);
            mockedStatic.verify(() -> UserValidator.validate(correctInstructor));
        }
    }

    @ParameterizedTest
    @MethodSource("setOfStandardPaymentRatesAndValidationResults")
    void shouldValidateStandardPaymentRate(BigDecimal standardPaymentRate, List<String> expected) {
        Instructor instructorWithVariableStandardPaymentRate =
            correctInstructor.toBuilder().standardPaymentRate(standardPaymentRate).build();

        List<String> resultOfValidation = InstructorValidator.validate(instructorWithVariableStandardPaymentRate);

        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfStandardPaymentRatesAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("Standard payment rate cannot be null.")),
            Arguments.of(BigDecimal.valueOf(1L), Collections.emptyList()),
            Arguments.of(BigDecimal.valueOf(100L), Collections.emptyList()),
            Arguments.of(BigDecimal.valueOf(-1L), Collections.singletonList("Standard payment rate cannot be lower than 0."))
        );
    }

    @ParameterizedTest
    @MethodSource("setOfAdditionalPaymentRatesAndValidationResults")
    void shouldValidateAdditionalPaymentRate(BigDecimal additionalPaymentRate, List<String> expected) {
        Instructor instructorWithVariableAdditionalPaymentRate =
            correctInstructor.toBuilder().additionalPaymentRate(additionalPaymentRate).build();

        List<String> resultOfValidation = InstructorValidator.validate(instructorWithVariableAdditionalPaymentRate);

        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfAdditionalPaymentRatesAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("Additional payment rate cannot be null.")),
            Arguments.of(BigDecimal.valueOf(1111L), Collections.emptyList()),
            Arguments.of(BigDecimal.valueOf(200L), Collections.emptyList()),
            Arguments.of(BigDecimal.valueOf(-1L), Collections.singletonList("Additional payment rate cannot be lower than 0."))
        );
    }

    @ParameterizedTest
    @MethodSource("setOfInstructorNumbersAndValidationResults")
    void shouldValidateInstructorNumber(String instructorNumber, List<String> expected) {
        Instructor instructorWithVariableInstructorNumber =
            correctInstructor.toBuilder().instructorNumber(instructorNumber).build();

        List<String> resultOfValidation = InstructorValidator.validate(instructorWithVariableInstructorNumber);

        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfInstructorNumbersAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("Instructor number cannot be null.")),
            Arguments.of("", Collections.singletonList("Instructor number must contain at least 1 character.")),
            Arguments.of("   ", Collections.singletonList("Instructor number must contain at least 1 character.")),
            Arguments.of("A-423 B C E+D", Collections.emptyList()),
            Arguments.of("B-012 A B", Collections.emptyList()),
            Arguments.of("A-43.x", Collections.emptyList())
        );
    }

    @ParameterizedTest
    @MethodSource("setOfLicenceExpireDatesAndValidationResults")
    void shouldValidateLicenceExpireDate(LocalDate licenceExpireDate, List<String> expected) {
        Instructor instructorWithVariableLicenceExpireDate
            = correctInstructor.toBuilder().licenceExpireDate(licenceExpireDate).build();

        List<String> resultOfValidation = InstructorValidator.validate(instructorWithVariableLicenceExpireDate);

        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfLicenceExpireDatesAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("Licence expire date cannot be null.")),
            Arguments.of(LocalDate.of(1995, 01, 31), Collections.emptyList()),
            Arguments.of(LocalDate.of(2025, 12, 15), Collections.emptyList()),
            Arguments.of(LocalDate.now(), Collections.emptyList())
        );
    }
}
