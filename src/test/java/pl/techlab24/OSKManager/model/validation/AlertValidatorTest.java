package pl.techlab24.OSKManager.model.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.techlab24.OSKManager.model.Alert;
import pl.techlab24.OSKManager.model.enums.AlertPriority;
import pl.techlab24.OSKManager.model.enums.AlertType;

class AlertValidatorTest {

    private Alert correctAlert;

    @BeforeEach
    void setup() {
        correctAlert = Alert.builder()
            .id(1L)
            .title("Test alert")
            .date(LocalDate.of(2021, 1, 23))
            .description(null)
            .alertType(AlertType.Review)
            .alertPriority(AlertPriority.Normal)
            .build();
    }

    @Test
    void shouldValidateCorrectAlert() {
        List<String> resultOfValidation = AlertValidator.validate(correctAlert);
        assertEquals(Collections.emptyList(), resultOfValidation);
    }

    @Test
    void shouldValidateNullAlert() {
        List<String> resultOfValidation = AlertValidator.validate(null);
        assertEquals(Collections.singletonList("Alert cannot be null."), resultOfValidation);
    }

    @ParameterizedTest
    @MethodSource("setOfTitlesAndValidationResults")
    void shouldValidateTitle(String title, List<String> expected) {
        Alert alertWithVariableTitle = correctAlert.toBuilder().title(title).build();

        List<String> resultOfValidation = AlertValidator.validate(alertWithVariableTitle);

        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfTitlesAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("Title cannot be null.")),
            Arguments.of("", Collections.singletonList("Title must contain at least 1 character.")),
            Arguments.of("   ", Collections.singletonList("Title must contain at least 1 character.")),
            Arguments.of("Test title", Collections.emptyList()),
            Arguments.of("* test *", Collections.emptyList())
        );
    }

    @ParameterizedTest
    @MethodSource("setOfDatesAndValidationResults")
    void shouldValidateDate(LocalDate date, List<String> expected) {
        Alert alertWithVariableDate = correctAlert.toBuilder().date(date).build();

        List<String> resultOfValidation = AlertValidator.validate(alertWithVariableDate);

        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfDatesAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("Date cannot be null.")),
            Arguments.of(LocalDate.of(2021, 01, 23), Collections.emptyList()),
            Arguments.of(LocalDate.now(), Collections.emptyList())
        );
    }

    @ParameterizedTest
    @MethodSource("setOfAlertTypesAndValidationResults")
    void shouldValidateAlertType(AlertType alertType, List<String> expected) {
        Alert alertWithVariableAlertType = correctAlert.toBuilder().alertType(alertType).build();

        List<String> resultOfValidation = AlertValidator.validate(alertWithVariableAlertType);

        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfAlertTypesAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("Alert type cannot be null.")),
            Arguments.of(AlertType.Review, Collections.emptyList()),
            Arguments.of(AlertType.Insurance, Collections.emptyList())
        );
    }

    @ParameterizedTest
    @MethodSource("setOfAlertPrioritiesAndValidationResults")
    void shouldValidateAlertPriority(AlertPriority alertPriority, List<String> expected) {
        Alert alertWithVariableAlertPriority = correctAlert.toBuilder().alertPriority(alertPriority).build();

        List<String> resultOfValidation = AlertValidator.validate(alertWithVariableAlertPriority);

        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfAlertPrioritiesAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("Alert priority cannot be null.")),
            Arguments.of(AlertPriority.Normal, Collections.emptyList()),
            Arguments.of(AlertPriority.Critical, Collections.emptyList()),
            Arguments.of(AlertPriority.SuperCritical, Collections.emptyList()),
            Arguments.of(AlertPriority.Urgent, Collections.emptyList())
        );
    }
}
