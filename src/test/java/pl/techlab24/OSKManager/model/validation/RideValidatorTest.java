package pl.techlab24.OSKManager.model.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import pl.techlab24.OSKManager.model.*;
import pl.techlab24.OSKManager.model.enums.RideType;
import pl.techlab24.OSKManager.model.enums.Sex;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

class RideValidatorTest {

    private Ride correctRide;
    private Instructor correctInstructor;
    private Car correctCar;

    @BeforeEach
    void setup() {
        CourseClient firstCourseClient = CourseClient.builder().customPrice(BigDecimal.valueOf(2500.00)).build();
        CourseClient secondCourseClient = CourseClient.builder().customPrice(BigDecimal.valueOf(2800.00)).build();

        Category categoryA = Category.builder().id(1L).categoryName("A").build();
        Category categoryB = Category.builder().id(2L).categoryName("B").build();

        correctInstructor = Instructor.builder()
                .id(1L)
                .email("test@test.com")
                .password("test_password_*#Eghicnshd765()")
                .name("Test Name")
                .secondName("Test Second Name")
                .surname("Test Surname")
                .sex(Sex.Other)
                .phoneNumber("+48123456789")
                .defaultPassword(null)
                .role("Test Role")
                .categories(new ArrayList<>(Arrays.asList(categoryA, categoryB)))
                .standardPaymentRate(BigDecimal.valueOf(100.00))
                .additionalPaymentRate(BigDecimal.valueOf(200.00))
                .instructorNumber("I-024")
                .licenceExpireDate(LocalDate.now().plusYears(1L))
                .build();

        correctCar = Car.builder()
                .mark("Ford")
                .model("Focus")
                .plate("WW 12345")
                .productionYear(2020)
                .endOfReview(LocalDate.of(2030, 12, 31))
                .endOfInsurance(LocalDate.of(2030, 12, 31))
                .instructors(new ArrayList<>())
                .rides(new ArrayList<>())
                .build();

        RideDetails firstRideDetails = RideDetails.builder().id(1L).rideType(RideType.Normal).duration(BigDecimal.valueOf(1)).build();
        RideDetails secondRideDetails = RideDetails.builder().id(2L).rideType(RideType.Additional).duration(BigDecimal.valueOf(1.5)).build();

        correctRide = Ride.builder()
                .id(1L)
                .date(LocalDate.of(2021, 01, 31))
                .instructor(correctInstructor)
                .car(correctCar)
                .rideDetails(Arrays.asList(firstRideDetails, secondRideDetails))
                .build();
    }

    @Test
    void shouldValidateCorrectRide() {
        // when
        List<String> resultOfValidation = RideValidator.validate(correctRide);

        // then
        assertEquals(Collections.emptyList(), resultOfValidation);
    }

    @Test
    void shouldValidateNullRide() {
        // when
        List<String> resultOfValidation = RideValidator.validate(null);

        // then
        assertEquals(Collections.singletonList("Ride cannot be null."), resultOfValidation);
    }

    @ParameterizedTest
    @MethodSource("setOfDatesAndValidationResults")
    void shouldValidateDate(LocalDate date, List<String> expected) {
        // given
        Ride rideWithVariableDate = correctRide.toBuilder().date(date).build();

        // when
        List<String> resultOfValidation = RideValidator.validate(rideWithVariableDate);

        // then
        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfDatesAndValidationResults() {
        return Stream.of(
                Arguments.of(null, Collections.singletonList("Date of ride cannot be null.")),
                Arguments.of(LocalDate.of(1995, 01, 31), Collections.emptyList()),
                Arguments.of(LocalDate.of(2025, 12, 15), Collections.emptyList()),
                Arguments.of(LocalDate.now(), Collections.emptyList())
        );
    }

    @Test
    void shouldValidateInstructorMethodCallValidateMethodFromInstructorValidatorClass() {
        try (MockedStatic<InstructorValidator> mockedStatic = mockStatic(InstructorValidator.class)) {
            // given
            mockedStatic.when(() -> InstructorValidator.validate(correctInstructor)).thenReturn(Collections.emptyList());

            // when
            List<String> result = RideValidator.validate(correctRide);

            // then
            assertEquals(Collections.emptyList(), result);
            mockedStatic.verify(() -> InstructorValidator.validate(correctInstructor));
        }
    }

    @Test
    void shouldValidateCarMethodCallValidateMethodFromCarValidatorClass() {
        try (MockedStatic<CarValidator> mockedStatic = mockStatic(CarValidator.class)) {
            // given
            mockedStatic.when(() -> CarValidator.validate(correctCar)).thenReturn(Collections.emptyList());

            // when
            List<String> result = RideValidator.validate(correctRide);

            // then
            assertEquals(Collections.emptyList(), result);
            mockedStatic.verify(() -> CarValidator.validate(correctCar));
        }
    }

    @Test
    void shouldValidateRideDetailsMethodCallValidateMethodFromRideDetailsValidatorClass() {
        try (MockedStatic<RideDetailsValidator> mockedStatic = mockStatic(RideDetailsValidator.class)) {
            // given
            mockedStatic.when(() -> RideDetailsValidator.validate(any())).thenReturn(Collections.emptyList());

            // when
            List<String> result = RideValidator.validate(correctRide);

            // then
            assertEquals(Collections.emptyList(), result);
            mockedStatic.verify(times(correctRide.getRideDetails().size()), () -> RideDetailsValidator.validate(any()));
        }
    }
}
