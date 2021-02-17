package pl.techlab24.OSKManager.model.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;;
import org.mockito.MockedStatic;
import pl.techlab24.OSKManager.model.*;
import pl.techlab24.OSKManager.model.enums.RideType;

class RideDetailsValidatorTest {

    private RideDetails correctRideDetails;
    private CourseClient correctCourseClient;

    @BeforeEach
    void setup() {
        correctCourseClient = CourseClient.builder()
            .client(new Client())
            .course(new Course())
            .ride(new Ride())
            .transactions(new ArrayList<>())
            .rideDetails(new ArrayList<>())
            .customPrice(BigDecimal.valueOf(1_000L))
            .build();

        correctRideDetails = RideDetails.builder()
            .id(1L)
            .rideType(RideType.Normal)
            .duration(BigDecimal.valueOf(1L))
            .ride(new Ride())
            .courseClient(correctCourseClient)
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

    @Test
    void shouldValidateCourseClientMethodCallValidateMethodFromCourseClientValidatorClass() {
        try (MockedStatic<CourseClientValidator> mockedStatic = mockStatic(CourseClientValidator.class)) {
            // given
            mockedStatic.when(() -> CourseClientValidator.validate(correctCourseClient)).thenReturn(Collections.emptyList());

            // when
            List<String> result = RideDetailsValidator.validate(correctRideDetails);

            // then
            assertEquals(Collections.emptyList(), result);
            mockedStatic.verify(() -> CourseClientValidator.validate(correctCourseClient));
        }
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
