package pl.techlab24.OSKManager.model.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.techlab24.OSKManager.model.Client;
import pl.techlab24.OSKManager.model.Course;
import pl.techlab24.OSKManager.model.CourseClient;

class CourseClientValidatorTest {

    private CourseClient correctCourseClient;

    @BeforeEach
    void setup() {
        correctCourseClient = new CourseClient(new Course(), new Client());
        correctCourseClient.setCustomPrice(BigDecimal.valueOf(1_000L));
    }

    @Test
    void shouldValidateCorrectCourseClient() {
        List<String> resultOfValidation = CourseClientValidator.validate(correctCourseClient);
        assertEquals(Collections.emptyList(), resultOfValidation);
    }

    @Test
    void shouldValidateNullCourseClient() {
        List<String> resultOfValidation = CourseClientValidator.validate(null);
        assertEquals(Collections.singletonList("Course client cannot be null."), resultOfValidation);
    }

    @ParameterizedTest
    @MethodSource("setOfCustomPricesAndValidationResults")
    void shouldValidateCustomPrice(BigDecimal customPrice, List<String> expected) {
        CourseClient courseWithVariableCustomPrice = correctCourseClient;
        correctCourseClient.setCustomPrice(customPrice);

        List<String> resultOfValidation = CourseClientValidator.validate(courseWithVariableCustomPrice);

        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfCustomPricesAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("Custom price cannot be null.")),
            Arguments.of(BigDecimal.valueOf(1_000L), Collections.emptyList()),
            Arguments.of(BigDecimal.valueOf(-1_000L), Collections.singletonList("Custom price cannot be lower than 0."))
        );
    }
}
