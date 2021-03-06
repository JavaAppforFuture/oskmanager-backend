package pl.techlab24.OSKManager.model.enums;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.Test;

public class TransactionTypeTest {

    @Test
    void transactionTypeValueCashShouldExist() {
        assertThat(TransactionType.valueOf("Cash"), is(notNullValue()));
    }

    @Test
    void transactionTypeValueCardShouldExist() {
        assertThat(TransactionType.valueOf("Card"), is(notNullValue()));
    }

    @Test
    void transactionTypeValueBanTransferShouldExist() {
        assertThat(TransactionType.valueOf("BankTransfer"), is(notNullValue()));
    }
}
