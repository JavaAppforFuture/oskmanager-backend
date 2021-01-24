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
import pl.techlab24.OSKManager.model.DriverCandidateProfile;

class DriverCandidateProfileValidatorTest {

    private DriverCandidateProfile correctDriverCandidateProfile;

    @BeforeEach
    void setup() {
        Category correctCategory = new Category(1L,
            "Test category",
            null,
            null,
            null);

        correctDriverCandidateProfile = new DriverCandidateProfile(1L,
            "Test number",
            correctCategory,
            LocalDate.of(2000, 1, 1),
            null
        );
    }

    @Test
    void shouldValidateCorrectDriverCandidateProfile() {
        List<String> resultOfValidation = DriverCandidateProfileValidator.validate(correctDriverCandidateProfile);
        assertEquals(Collections.emptyList(), resultOfValidation);
    }

    @Test
    void shouldValidateNullDriverCandidateProfile() {
        List<String> resultOfValidation = DriverCandidateProfileValidator.validate(null);
        assertEquals(Collections.singletonList("Driver candidate profile cannot be null."), resultOfValidation);
    }

    @ParameterizedTest
    @MethodSource("setOfNumbersAndValidationResults")
    void shouldValidateNumber(String number, List<String> expected) {
        DriverCandidateProfile driverCandidateProfileWithVariableNumber = correctDriverCandidateProfile;
        driverCandidateProfileWithVariableNumber.setNumber(number);

        List<String> resultOfValidation = DriverCandidateProfileValidator.validate(driverCandidateProfileWithVariableNumber);

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
        DriverCandidateProfile driverCandidateProfileWithVariableCategory = correctDriverCandidateProfile;
        correctDriverCandidateProfile.setCategory(category);

        List<String> resultOfValidation = DriverCandidateProfileValidator.validate(driverCandidateProfileWithVariableCategory);

        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfCategoriesAndValidationResults() {
        Category correctCategory = new Category(1L,
            "Test category",
            null,
            null,
            null);

        Category incorrectCategoryWithNullCategoryName = new Category(1L,
            null,
            null,
            null,
            null);

        Category incorrectCategoryWithEmptyCategoryName = new Category(1L,
            "",
            null,
            null,
            null);

        return Stream.of(
            Arguments.of(null, Collections.singletonList("Category cannot be null.")),
            Arguments.of(correctCategory, Collections.emptyList()),
            Arguments.of(incorrectCategoryWithNullCategoryName, Collections.singletonList("Category name cannot be null.")),
            Arguments.of(incorrectCategoryWithEmptyCategoryName, Collections.singletonList("Category name must contain at least 1 character."))
        );
    }

    @ParameterizedTest
    @MethodSource("setOfAddedDatesAndValidationResults")
    void shouldValidateAddedDate(LocalDate addedDate, List<String> expected) {
        DriverCandidateProfile driverCandidateProfileWithVariableAddedDate = correctDriverCandidateProfile;
        driverCandidateProfileWithVariableAddedDate.setAddedDate(addedDate);

        List<String> resultOfValidation = DriverCandidateProfileValidator.validate(driverCandidateProfileWithVariableAddedDate);

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
