package pl.techlab24.OSKManager.model.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import pl.techlab24.OSKManager.model.Car;

class CarValidatorTest {

    private Car correctCar;

    @BeforeEach
    void setup() {
        correctCar = Car.builder()
            .id(1L)
            .mark("Ford")
            .model("Focus")
            .plate("WW 12345")
            .productionYear(2020)
            .endOfReview(LocalDate.of(2030, 12, 31))
            .endOfInsurance(LocalDate.of(2030, 12, 31))
            .instructors(new ArrayList<>())
            .rides(new ArrayList<>())
            .build();
    }

    @Test
    void shouldValidateCorrectCar() {
        // when
        List<String> resultOfValidation = CarValidator.validate(correctCar);

        // then
        assertEquals(Collections.emptyList(), resultOfValidation);
    }

    @Test
    void shouldValidateNullCar() {
        // when
        List<String> resultOfValidation = CarValidator.validate(null);

        // then
        assertEquals(Collections.singletonList("Car cannot be null."), resultOfValidation);
    }

    @ParameterizedTest
    @MethodSource("setOfMarksAndValidationResults")
    void shouldValidateMark(String mark, List<String> expected) {
        // given
        Car carWithVariableName = correctCar.toBuilder().mark(mark).build();

        // when
        List<String> resultOfValidation = CarValidator.validate(carWithVariableName);

        // then
        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfMarksAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("Mark cannot be null.")),
            Arguments.of("", Collections.singletonList("Mark must contain at least 1 character.")),
            Arguments.of("   ", Collections.singletonList("Mark must contain at least 1 character.")),
            Arguments.of("Mazda", Collections.emptyList()),
            Arguments.of("AAA", Collections.emptyList())
        );
    }

    @ParameterizedTest
    @MethodSource("setOfModelsAndValidationResults")
    void shouldValidateModel(String model, List<String> expected) {
        // given
        Car carWithVariableModel = correctCar.toBuilder().model(model).build();

        // when
        List<String> resultOfValidation = CarValidator.validate(carWithVariableModel);

        // then
        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfModelsAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("Model cannot be null.")),
            Arguments.of("", Collections.singletonList("Model must contain at least 1 character.")),
            Arguments.of("   ", Collections.singletonList("Model must contain at least 1 character.")),
            Arguments.of("CX5", Collections.emptyList()),
            Arguments.of("AAA", Collections.emptyList())
        );
    }

    @ParameterizedTest
    @MethodSource("setOfPlatesAndValidationResults")
    void shouldValidatePlate(String plate, List<String> expected) {
        // given
        Car carWithVariablePlate = correctCar.toBuilder().plate(plate).build();

        // when
        List<String> resultOfValidation = CarValidator.validate(carWithVariablePlate);

        // then
        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfPlatesAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("Plate cannot be null.")),
            Arguments.of("", Collections.singletonList("Plate must contain at least 1 character.")),
            Arguments.of("   ", Collections.singletonList("Plate must contain at least 1 character.")),
            Arguments.of("DW 777AX", Collections.emptyList()),
            Arguments.of("AAA", Collections.emptyList())
        );
    }

    @ParameterizedTest
    @MethodSource("setOfProductionYearsAndValidationResults")
    void shouldValidateProductionYear(Integer productionYear, List<String> expected) {
        // given
        Car carWithVariableProductionYear = correctCar.toBuilder().productionYear(productionYear).build();

        // when
        List<String> resultOfValidation = CarValidator.validate(carWithVariableProductionYear);

        // then
        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfProductionYearsAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("Production year cannot be null.")),
            Arguments.of(-1, Collections.singletonList("Production year cannot be lower than 1900 or greater than current year.")),
            Arguments.of(1850, Collections.singletonList("Production year cannot be lower than 1900 or greater than current year.")),
            Arguments.of(3000, Collections.singletonList("Production year cannot be lower than 1900 or greater than current year.")),
            Arguments.of(2000, Collections.emptyList())
        );
    }

    @ParameterizedTest
    @MethodSource("setOfEndOfReviewDatesAndValidationResults")
    void shouldValidateEndOfReviewDate(LocalDate endOfReviewDate, List<String> expected) {
        // given
        Car carWithVariableEndOfReviewDate = correctCar.toBuilder().endOfReview(endOfReviewDate).build();

        // when
        List<String> resultOfValidation = CarValidator.validate(carWithVariableEndOfReviewDate);

        // then
        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfEndOfReviewDatesAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("End of review date cannot be null.")),
            Arguments.of(LocalDate.of(2025, 12, 15), Collections.emptyList()),
            Arguments.of(LocalDate.now(), Collections.emptyList())
        );
    }

    @ParameterizedTest
    @MethodSource("setOfEndOfInsuranceDatesAndValidationResults")
    void shouldValidateEndOfInsuranceDate(LocalDate endOfInsuranceDate, List<String> expected) {
        // given
        Car carWithVariableEndOfInsuranceDate = correctCar.toBuilder().endOfInsurance(endOfInsuranceDate).build();

        // when
        List<String> resultOfValidation = CarValidator.validate(carWithVariableEndOfInsuranceDate);

        // then
        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfEndOfInsuranceDatesAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("End of insurance date cannot be null.")),
            Arguments.of(LocalDate.of(2025, 12, 15), Collections.emptyList()),
            Arguments.of(LocalDate.now(), Collections.emptyList())
        );
    }
}
