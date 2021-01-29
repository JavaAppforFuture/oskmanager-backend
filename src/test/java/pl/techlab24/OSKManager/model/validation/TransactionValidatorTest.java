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
        correctTransaction = new Transaction(1L,
            new CourseClient(),
            LocalDate.of(2020, 10, 10),
            BigDecimal.valueOf(1_000L),
            TransactionType.Cash,
            null
        );
    }

    @Test
    void shouldValidateCorrectTransaction() {
        List<String> resultOfValidation = TransactionValidator.validate(correctTransaction);
        assertEquals(Collections.emptyList(), resultOfValidation);
    }

    @Test
    void shouldValidateNullTransaction() {
        List<String> resultOfValidation = TransactionValidator.validate(null);
        assertEquals(Collections.singletonList("Transaction cannot be null."), resultOfValidation);
    }

    @ParameterizedTest
    @MethodSource("setOfTransactionDatesAndValidationResults")
    void shouldValidateTransactionDate(LocalDate transactionDate, List<String> expected) {
        Transaction transactionWithVariableDate = correctTransaction;
        transactionWithVariableDate.setDate(transactionDate);

        List<String> resultOfValidation = TransactionValidator.validate(transactionWithVariableDate);

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
        Transaction transactionWithVariableValue = correctTransaction;
        transactionWithVariableValue.setValue(value);

        List<String> resultOfValidation = TransactionValidator.validate(transactionWithVariableValue);

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
        Transaction transactionWithNullTransactionType = correctTransaction;
        transactionWithNullTransactionType.setTransactionType(null);

        // when
        List<String> resultOfValidation = TransactionValidator.validate(transactionWithNullTransactionType);

        // then
        assertEquals(Collections.singletonList("Transaction type cannot be null."), resultOfValidation);
    }
}
