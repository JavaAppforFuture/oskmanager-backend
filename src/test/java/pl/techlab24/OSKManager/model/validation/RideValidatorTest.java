package pl.techlab24.OSKManager.model.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import pl.techlab24.OSKManager.model.Car;
import pl.techlab24.OSKManager.model.Category;
import pl.techlab24.OSKManager.model.CourseClient;
import pl.techlab24.OSKManager.model.Instructor;
import pl.techlab24.OSKManager.model.Ride;
import pl.techlab24.OSKManager.model.RideDetails;
import pl.techlab24.OSKManager.model.enums.RideType;
import pl.techlab24.OSKManager.model.enums.Sex;

class RideValidatorTest {

    private Ride correctRide;
    private Instructor correctInstructor;
    private Car correctCar;

    @BeforeEach
    void setup() {
        CourseClient firstCourseClient = new CourseClient();
        CourseClient secondCourseClient = new CourseClient();
        firstCourseClient.setCustomPrice(BigDecimal.valueOf(2500L));
        secondCourseClient.setCustomPrice(BigDecimal.valueOf(2800L));

        Category categoryA = new Category(1L,
            "A",
            null,
            null,
            null);

        Category categoryB = new Category(2L,
            "B",
            null,
            null,
            null);

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

        correctCar = new Car(1L,
            "Ford",
            "Focus",
            "WW 12345",
            2020,
            LocalDate.of(2030, 12, 31),
            LocalDate.of(2030, 12, 31),
            new ArrayList<>(),
            new ArrayList<>()
        );

        RideDetails firstRideDetails = new RideDetails();
        RideDetails secondRideDetails = new RideDetails();
        firstRideDetails.setId(1L);
        firstRideDetails.setRideType(RideType.Normal);
        firstRideDetails.setDuration(BigDecimal.valueOf(1));
        secondRideDetails.setId(2L);
        secondRideDetails.setRideType(RideType.Additional);
        secondRideDetails.setDuration(BigDecimal.valueOf(1.5));

        correctRide = new Ride(1L,
            LocalDate.of(2021, 01, 31),
            Arrays.asList(firstCourseClient, secondCourseClient),
            correctInstructor,
            correctCar,
            Arrays.asList(firstRideDetails, secondRideDetails)
        );
    }

    @Test
    void shouldValidateCorrectRide() {
        List<String> resultOfValidation = RideValidator.validate(correctRide);
        assertEquals(Collections.emptyList(), resultOfValidation);
    }

    @Test
    void shouldValidateNullRide() {
        List<String> resultOfValidation = RideValidator.validate(null);
        assertEquals(Collections.singletonList("Ride cannot be null."), resultOfValidation);
    }

    @ParameterizedTest
    @MethodSource("setOfDatesAndValidationResults")
    void shouldValidateDate(LocalDate date, List<String> expected) {
        Ride rideWithVariableDate = correctRide.toBuilder().date(date).build();
        System.out.println(rideWithVariableDate.getDate());
        List<String> resultOfValidation = RideValidator.validate(rideWithVariableDate);

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
    void shouldValidateCourseClientMethodCallValidateMethodFromCourseClientValidatorClass() {
        try (MockedStatic<CourseClientValidator> mockedStatic = mockStatic(CourseClientValidator.class)) {
            // given
            mockedStatic.when(() -> CourseClientValidator.validate(any())).thenReturn(Collections.emptyList());

            // when
            List<String> result = RideValidator.validate(correctRide);

            // then
            assertEquals(Collections.emptyList(), result);
            mockedStatic.verify(times(correctRide.getCourseClients().size()), () -> CourseClientValidator.validate(any()));
        }
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
