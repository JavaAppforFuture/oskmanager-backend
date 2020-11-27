package pl.techlab24.OSKManager.model.enums;

public enum TransactionType {

    Cash("Cash"),
    Card("Card"),
    BankTransfer("BankTransfer");

    private final String transactionTypeDescription;

    TransactionType(String transactionTypeDescription) {
        this.transactionTypeDescription = transactionTypeDescription;
    }
}
