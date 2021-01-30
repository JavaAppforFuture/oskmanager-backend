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
import pl.techlab24.OSKManager.model.Category;
import pl.techlab24.OSKManager.model.Course;
import pl.techlab24.OSKManager.model.DriverCandidateProfile;
import pl.techlab24.OSKManager.model.Instructor;

class CategoryValidatorTest {

    private Category correctCategory;

    @BeforeEach
    void setup() {
        correctCategory = Category.builder()
            .id(1L)
            .categoryName("Test category")
            .course(new Course())
            .driverCandidateProfile(new DriverCandidateProfile())
            .instructor(new Instructor())
            .build();
    }

    @Test
    void shouldValidateCorrectCategory() {
        List<String> resultOfValidation = CategoryValidator.validate(correctCategory);
        assertEquals(Collections.emptyList(), resultOfValidation);
    }

    @Test
    void shouldValidateNullCategory() {
        List<String> resultOfValidation = CategoryValidator.validate(null);
        assertEquals(Collections.singletonList("Category cannot be null."), resultOfValidation);
    }

    @ParameterizedTest
    @MethodSource("setOfCategoryNamesAndValidationResults")
    void shouldValidateTitle(String categoryName, List<String> expected) {
        Category categoryWithVariableName = correctCategory.toBuilder().categoryName(categoryName).build();

        List<String> resultOfValidation = CategoryValidator.validate(categoryWithVariableName);

        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfCategoryNamesAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("Category name cannot be null.")),
            Arguments.of("", Collections.singletonList("Category name must contain at least 1 character.")),
            Arguments.of("   ", Collections.singletonList("Category name must contain at least 1 character.")),
            Arguments.of("A", Collections.emptyList()),
            Arguments.of("A1", Collections.emptyList()),
            Arguments.of("B", Collections.emptyList()),
            Arguments.of("CD", Collections.emptyList()),
            Arguments.of("* test *", Collections.emptyList())
        );
    }
}
