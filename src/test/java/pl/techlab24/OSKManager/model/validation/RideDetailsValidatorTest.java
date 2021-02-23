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
import pl.techlab24.OSKManager.model.CourseClient;
import pl.techlab24.OSKManager.model.Ride;
import pl.techlab24.OSKManager.model.RideDetails;
import pl.techlab24.OSKManager.model.enums.RideType;

class RideDetailsValidatorTest {

    private RideDetails correctRideDetails;

    @BeforeEach
    void setup() {
        correctRideDetails = RideDetails.builder()
            .id(1L)
            .rideType(RideType.Normal)
            .duration(BigDecimal.valueOf(1L))
            .ride(new Ride())
            .courseClient(new CourseClient())
            .build();
    }

    @Test
    void shouldValidateCorrectRideDetails() {
        // when
        List<String> resultOfValidation = RideDetailsValidator.validate(correctRideDetails);

        // then
        assertEquals(Collections.emptyList(), resultOfValidation);
    }

    @Test
    void shouldValidateNullRideDetails() {
        // when
        List<String> resultOfValidation = RideDetailsValidator.validate(null);

        // then
        assertEquals(Collections.singletonList("Ride details cannot be null."), resultOfValidation);
    }

    @Test
    void shouldValidateNullRideType() {
        // given
        RideDetails rideDetailsWithNullRideType = correctRideDetails.toBuilder().rideType(null).build();

        // when
        List<String> resultOfValidation = RideDetailsValidator.validate(rideDetailsWithNullRideType);

        // then
        assertEquals(Collections.singletonList("Ride type cannot be null."), resultOfValidation);
    }

    @ParameterizedTest
    @MethodSource("setOfRideDurationsAndValidationResults")
    void shouldValidateRideDuration(BigDecimal rideDuration, List<String> expected) {
        // given
        RideDetails rideDetailsWithVariableRideDuration = correctRideDetails.toBuilder().duration(rideDuration).build();

        // when
        List<String> resultOfValidation = RideDetailsValidator.validate(rideDetailsWithVariableRideDuration);

        // then
        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfRideDurationsAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("Ride duration cannot be null.")),
            Arguments.of(BigDecimal.valueOf(1L), Collections.emptyList()),
            Arguments.of(BigDecimal.valueOf(100L), Collections.emptyList()),
            Arguments.of(BigDecimal.valueOf(-1L), Collections.singletonList("Ride duration cannot be lower than 0."))
        );
    }
}
