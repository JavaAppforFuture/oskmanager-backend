package pl.techlab24.OSKManager.model.validation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.techlab24.OSKManager.model.Transaction;
import pl.techlab24.OSKManager.model.enums.TransactionType;

public class TransactionValidator extends Validator {

    public static List<String> validate(Transaction transaction) {
        if (transaction == null) {
            return Collections.singletonList("Transaction cannot be null.");
        }

        List<String> result = new ArrayList<>();

        addResultOfValidation(result, validateDate(transaction.getDate()));
        addResultOfValidation(result, validateValue(transaction.getValue()));
        addResultOfValidation(result, validateTransactionType(transaction.getTransactionType()));

        return result;
    }

    private static String validateDate(LocalDate date) {
        if (date == null) {
            return  "Date of transaction cannot be null.";
        }
        return null;
    }

    private static String validateValue(BigDecimal value) {
        if (value == null) {
            return "Value cannot be null.";
        }
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            return "Value cannot be lower than 0.";
        }
        return null;
    }

    private static String validateTransactionType(TransactionType transactionType) {
        if (transactionType == null) {
            return "Transaction type cannot be null.";
        }
        return null;
    }
}
