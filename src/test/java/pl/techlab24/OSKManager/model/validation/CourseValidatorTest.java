package pl.techlab24.OSKManager.model.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
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

class CourseValidatorTest {

    private Course correctCourse;

    @BeforeEach
    void setup() {
        Category correctCategory = Category.builder()
            .id(1L)
            .categoryName("Test category")
            .course(new Course())
            .driverCandidateProfile(new DriverCandidateProfile())
            .instructor(new Instructor())
            .build();

        correctCourse = Course.builder()
            .id(1L)
            .courseNumber("AAA-1")
            .startDate(LocalDate.of(2021, 1, 25))
            .endDate(LocalDate.of(2100, 12, 31))
            .category(correctCategory)
            .defaultPrice(BigDecimal.valueOf(1_000L))
            .courseClients(new ArrayList<>())
            .build();
    }

    @Test
    void shouldValidateCorrectCourse() {
        // when
        List<String> resultOfValidation = CourseValidator.validate(correctCourse);

        // then
        assertEquals(Collections.emptyList(), resultOfValidation);
    }

    @Test
    void shouldValidateNullCourse() {
        // when
        List<String> resultOfValidation = CourseValidator.validate(null);

        // then
        assertEquals(Collections.singletonList("Course cannot be null."), resultOfValidation);
    }

    @ParameterizedTest
    @MethodSource("setOfCourseNumbersAndValidationResults")
    void shouldValidateCourseNumber(String courseNumber, List<String> expected) {
        // given
        Course courseWithVariableCourseNumber = correctCourse.toBuilder().courseNumber(courseNumber).build();

        // when
        List<String> resultOfValidation = CourseValidator.validate(courseWithVariableCourseNumber);

        // then
        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfCourseNumbersAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("Course number cannot be null.")),
            Arguments.of("", Collections.singletonList("Course number must contain at least 1 character.")),
            Arguments.of("   ", Collections.singletonList("Course number must contain at least 1 character.")),
            Arguments.of("1A43X", Collections.emptyList()),
            Arguments.of("AAA-1", Collections.emptyList()),
            Arguments.of("A 045*", Collections.emptyList())
        );
    }

    @ParameterizedTest
    @MethodSource("setOfStartDatesAndValidationResults")
    void shouldValidateStartDate(LocalDate startDate, List<String> expected) {
        // given
        Course courseWithVariableStartDate = correctCourse.toBuilder().startDate(startDate).build();

        // when
        List<String> resultOfValidation = CourseValidator.validate(courseWithVariableStartDate);

        // then
        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfStartDatesAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("Course start date cannot be null.")),
            Arguments.of(LocalDate.now(), Collections.emptyList()),
            Arguments.of(LocalDate.of(2010, 1, 1), Collections.emptyList()),
            Arguments.of(LocalDate.of(2021, 1, 25), Collections.emptyList()),
            Arguments.of(LocalDate.of(2030, 1, 1), Collections.emptyList())
        );
    }

    @ParameterizedTest
    @MethodSource("setOfCategoriesAndValidationResults")
    void shouldValidateCategory(Category category, List<String> expected) {
        // given
        Course courseWithVariableCategory = correctCourse.toBuilder().category(category).build();

        // when
        List<String> resultOfValidation = CourseValidator.validate(courseWithVariableCategory);

        // then
        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfCategoriesAndValidationResults() {
        return SetsOfVariablesAndValidationResults.setOfCategoriesAndValidationResults();
    }

    @ParameterizedTest
    @MethodSource("setOfDefaultPricesAndValidationResults")
    void shouldValidateDefaultPrice(BigDecimal defaultPrice, List<String> expected) {
        // given
        Course courseWithVariableDefaultPrice = correctCourse.toBuilder().defaultPrice(defaultPrice).build();

        // when
        List<String> resultOfValidation = CourseValidator.validate(courseWithVariableDefaultPrice);

        // then
        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfDefaultPricesAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("Default price cannot be null.")),
            Arguments.of(BigDecimal.valueOf(1_000L), Collections.emptyList()),
            Arguments.of(BigDecimal.valueOf(-1_000L), Collections.singletonList("Default price cannot be lower than 0."))
        );
    }
}
