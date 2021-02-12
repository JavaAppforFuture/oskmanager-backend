package pl.techlab24.OSKManager.model.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.techlab24.OSKManager.model.CourseClient;
import pl.techlab24.OSKManager.model.Transaction;
import pl.techlab24.OSKManager.model.enums.TransactionType;

class TransactionValidatorTest {

    private Transaction correctTransaction;

    @BeforeEach
    void setup() {
        correctTransaction = Transaction.builder()
            .id(1L)
            .courseClient(new CourseClient())
            .date(LocalDate.of(2020, 10, 10))
            .value(BigDecimal.valueOf(1_000L))
            .transactionType(TransactionType.Cash)
            .description(null)
            .build();
    }

    @Test
    void shouldValidateCorrectTransaction() {
        // when
        List<String> resultOfValidation = TransactionValidator.validate(correctTransaction);

        // then
        assertEquals(Collections.emptyList(), resultOfValidation);
    }

    @Test
    void shouldValidateNullTransaction() {
        // when
        List<String> resultOfValidation = TransactionValidator.validate(null);

        // then
        assertEquals(Collections.singletonList("Transaction cannot be null."), resultOfValidation);
    }

    @ParameterizedTest
    @MethodSource("setOfTransactionDatesAndValidationResults")
    void shouldValidateTransactionDate(LocalDate transactionDate, List<String> expected) {
        // given
        Transaction transactionWithVariableDate = correctTransaction.toBuilder().date(transactionDate).build();

        // when
        List<String> resultOfValidation = TransactionValidator.validate(transactionWithVariableDate);

        // then
        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfTransactionDatesAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("Date of transaction cannot be null.")),
            Arguments.of(LocalDate.now(), Collections.emptyList()),
            Arguments.of(LocalDate.of(2000, 1, 1), Collections.emptyList()),
            Arguments.of(LocalDate.now().plusYears(1L), Collections.singletonList("Date of transaction cannot be later than now."))
        );
    }

    @ParameterizedTest
    @MethodSource("setOfTransactionValuesAndValidationResults")
    void shouldValidateTransactionValue(BigDecimal value, List<String> expected) {
        // given
        Transaction transactionWithVariableValue = correctTransaction.toBuilder().value(value).build();

        // when
        List<String> resultOfValidation = TransactionValidator.validate(transactionWithVariableValue);

        // then
        assertEquals(expected, resultOfValidation);
    }

    private static Stream<Arguments> setOfTransactionValuesAndValidationResults() {
        return Stream.of(
            Arguments.of(null, Collections.singletonList("Transaction value cannot be null.")),
            Arguments.of(BigDecimal.valueOf(1L), Collections.emptyList()),
            Arguments.of(BigDecimal.valueOf(100L), Collections.emptyList()),
            Arguments.of(BigDecimal.valueOf(-1L), Collections.singletonList("Transaction value cannot be lower than 0."))
        );
    }

    @Test
    void shouldValidateNullTransactionType() {
        // given
        Transaction transactionWithNullTransactionType = correctTransaction.toBuilder().transactionType(null).build();

        // when
        List<String> resultOfValidation = TransactionValidator.validate(transactionWithNullTransactionType);

        // then
        assertEquals(Collections.singletonList("Transaction type cannot be null."), resultOfValidation);
    }
}
