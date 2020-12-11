package pl.techlab24.OSKManager.model.validation;

import pl.techlab24.OSKManager.model.Transaction;
import pl.techlab24.OSKManager.model.enums.TransactionType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransactionValidator extends Validator {

    public static List<String> validate(Transaction transaction) {
        if (transaction == null) {
            return Collections.singletonList("User cannot be null");
        }

        List<String> result = new ArrayList<>();

        //add validator result here

        return result;
    }

    private static String validateDate() {


        return null;
    }

    private static String validateTransactionValue(BigDecimal value) {


        return null;
    }

    private static String validateTransactionType(TransactionType transactionType) {
        if (transactionType == null) {
            return "Transaction type cannot be null";
        }

        return null;
    }

}
