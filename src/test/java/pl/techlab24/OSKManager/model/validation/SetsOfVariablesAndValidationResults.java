package pl.techlab24.OSKManager.model.validation;

import java.util.Collections;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;
import pl.techlab24.OSKManager.model.Category;

final class SetsOfVariablesAndValidationResults {

    static Stream<Arguments> setOfCategoriesAndValidationResults() {
        Category correctCategory = Category.builder()
            .id(1L)
            .categoryName("Test category")
            .build();

        Category incorrectCategoryWithNullCategoryName = Category.builder()
            .id(1L)
            .categoryName(null)
            .build();

        Category incorrectCategoryWithEmptyCategoryName = Category.builder()
            .id(1L)
            .categoryName("")
            .build();

        return Stream.of(
            Arguments.of(null, Collections.singletonList("Category cannot be null.")),
            Arguments.of(correctCategory, Collections.emptyList()),
            Arguments.of(incorrectCategoryWithNullCategoryName, Collections.singletonList("Category name cannot be null.")),
            Arguments.of(incorrectCategoryWithEmptyCategoryName, Collections.singletonList("Category name must contain at least 1 character."))
        );
    }
}
