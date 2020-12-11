package pl.techlab24.OSKManager.model.validation;

import pl.techlab24.OSKManager.model.Transaction;
import pl.techlab24.OSKManager.model.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransactionValidator extends Validator {

    public static List<String> validate(Transaction transaction) {
        if (transaction == null) {
            return Collections.singletonList("User cannot be null");
        }

        List<String> result = new ArrayList<>();

        addResultOfValidation(result, validateDate(transaction.getDate()));
        addResultOfValidation(result, validateTransactionValue(transaction.getValue()));
        addResultOfValidation(result, validateTransactionType(transaction.getTransactionType()));

        return result;
    }

    private static String validateDate(LocalDate date) {
        if (date == null) {
            return  "Date of transaction cannot be null";
        }

        return null;
    }

    private static String validateTransactionValue(BigDecimal value) {
        if (value == null) {
            return "Transaction value cannot be null";

        }

        if (value.compareTo(BigDecimal.ZERO) < 0) {
            return "Transaction value cannot be lower than 0";

        }

        return null;
    }

    private static String validateTransactionType(TransactionType transactionType) {
        if (transactionType == null) {
            return "Transaction type cannot be null";
        }

        return null;
    }

}
