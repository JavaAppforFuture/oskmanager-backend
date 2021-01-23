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
import pl.techlab24.OSKManager.model.Instructor;
import pl.techlab24.OSKManager.model.Ride;

class CarValidatorTest {

    private Car correctCar;

    @BeforeEach
    void setup() {
        correctCar = new Car(1L,
            "Ford",
            "Focus",
            "WW 12345",
            2020,
            LocalDate.of(2030, 12, 31),
            LocalDate.of(2030, 12, 31),
            new ArrayList<Instructor>(),
            new ArrayList<Ride>()
        );
    }

    @Test
    void shouldValidateCorrectCar() {
        List<String> resultOfValidation = CarValidator.validate(correctCar);
        assertEquals(Collections.emptyList(), resultOfValidation);
    }

    @Test
    void shouldValidateNullCar() {
        List<String> resultOfValidation = CarValidator.validate(null);
        assertEquals(Collections.singletonList("Car cannot be null."), resultOfValidation);
    }

    @ParameterizedTest
    @MethodSource("setOfMarksAndValidationResults")
    void shouldValidateMark(String mark, List<String> expected) {
        Car carWithVariableName = correctCar;
        carWithVariableName.setMark(mark);

        List<String> resultOfValidation = CarValidator.validate(carWithVariableName);

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
        Car carWithVariableModel = correctCar;
        carWithVariableModel.setModel(model);

        List<String> resultOfValidation = CarValidator.validate(carWithVariableModel);

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
        Car carWithVariablePlate = correctCar;
        carWithVariablePlate.setPlate(plate);

        List<String> resultOfValidation = CarValidator.validate(carWithVariablePlate);

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
        Car carWithVariableProductionYear = correctCar;
        carWithVariableProductionYear.setProductionYear(productionYear);

        List<String> resultOfValidation = CarValidator.validate(carWithVariableProductionYear);

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
        Car carWithVariableEndOfReviewDate = correctCar;
        carWithVariableEndOfReviewDate.setEndOfReview(endOfReviewDate);

        List<String> resultOfValidation = CarValidator.validate(carWithVariableEndOfReviewDate);

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
        Car carWithVariableEndOfInsuranceDate = correctCar;
        carWithVariableEndOfInsuranceDate.setEndOfInsurance(endOfInsuranceDate);

        List<String> resultOfValidation = CarValidator.validate(carWithVariableEndOfInsuranceDate);

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
