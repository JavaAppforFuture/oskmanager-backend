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

class CategoryValidatorTest {

    private Category correctCategory;

    @BeforeEach
    void setup() {
        correctCategory = new Category(1L,
            "Test category",
            null,
            null,
            null);
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
        Category categoryWithVariableName = correctCategory;
        categoryWithVariableName.setCategoryName(categoryName);

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
