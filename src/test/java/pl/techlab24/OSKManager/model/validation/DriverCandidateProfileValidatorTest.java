package pl.techlab24.OSKManager.model.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.techlab24.OSKManager.model.Category;
import pl.techlab24.OSKManager.model.Client;
import pl.techlab24.OSKManager.model.Course;
import pl.techlab24.OSKManager.model.DriverCandidateProfile;
import pl.techlab24.OSKManager.model.Instructor;

class DriverCandidateProfileValidatorTest {

    private DriverCandidateProfile correctDriverCandidateProfile;

    @BeforeEach
    void setup() {
        Category correctCategory = Category.builder()
            .id(1L)
            .categoryName("Test category")
            .course(new Course())
            .driverCandidateProfile(new DriverCandidateProfile())
            .instructor(new Instructor())
            .build();

        correctDriverCandidateProfile = DriverCandidateProfile.builder()
            .id(1L)
            .number("Test number")
            .category(correctCategory)
            .addedDate(LocalDate.of(2000, 1, 1))
            .client(new Client())
            .build();
    }

    @Test
    void shouldValidateCorrectDriverCandidateProfile() {
        // when
        List<String> resultOfValidation = DriverCandidateProfileValidator.validate(correctDriverCandidateProfile);

        // then
        assertEquals(Collections.emptyList(), resultOfValidation);
    }

    @Test
    void shouldValidateNullDriverCandidateProfile() {
        // when
        List<String> resultOfValidation = DriverCandidateProfileValidator.validate(null);

        // then
        assertEquals(Collections.singletonList("Driver candidate profile cannot be null."), resultOfValidation);
    }

    @ParameterizedTest
    @MethodSource("setOfNumbersAndValidationResults")
    void shouldValidateNumber(String number, List<String> expected) {
        // given
        DriverCandidateProfile driverCandidateProfileWithVariableNumber =
            correctDriverCandidateProfile.toBuilder().number(number).build();

        // when
        List<String> resultOfValidation = DriverCandidateProfileValidator.validate(driverCandidateProfileWithVariableNumber);

        // then
        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfNumbersAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("Driver candidate profile number cannot be null.")),
            Arguments.of("", Collections.singletonList("Driver candidate profile number must contain at least 1 character.")),
            Arguments.of("   ", Collections.singletonList("Driver candidate profile number must contain at least 1 character.")),
            Arguments.of("Test number", Collections.emptyList()),
            Arguments.of("AAA-1", Collections.emptyList()),
            Arguments.of("* test 0123", Collections.emptyList())
        );
    }

    @ParameterizedTest
    @MethodSource("setOfCategoriesAndValidationResults")
    void shouldValidateCategory(Category category, List<String> expected) {
        // given
        DriverCandidateProfile driverCandidateProfileWithVariableCategory =
            correctDriverCandidateProfile.toBuilder().category(category).build();

        // when
        List<String> resultOfValidation = DriverCandidateProfileValidator.validate(driverCandidateProfileWithVariableCategory);

        // then
        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfCategoriesAndValidationResults() {
        return SetsOfVariablesAndValidationResults.setOfCategoriesAndValidationResults();
    }

    @ParameterizedTest
    @MethodSource("setOfAddedDatesAndValidationResults")
    void shouldValidateAddedDate(LocalDate addedDate, List<String> expected) {
        // given
        DriverCandidateProfile driverCandidateProfileWithVariableAddedDate =
            correctDriverCandidateProfile.toBuilder().addedDate(addedDate).build();

        // when
        List<String> resultOfValidation = DriverCandidateProfileValidator.validate(driverCandidateProfileWithVariableAddedDate);

        // then
        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfAddedDatesAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("The date the profile was added cannot be null.")),
            Arguments.of(LocalDate.now(), Collections.emptyList()),
            Arguments.of(LocalDate.of(2000, 1, 1), Collections.emptyList()),
            Arguments.of(LocalDate.now().plusYears(1L), Collections.singletonList("The date the profile was added cannot be later than now."))
        );
    }
}
