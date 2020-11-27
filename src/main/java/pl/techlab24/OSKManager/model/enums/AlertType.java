package pl.techlab24.OSKManager.model.enums;

public enum AlertType {

    Review("Review"),
    Insurance("Insurance");

    private final String alertTypeDescription;

    AlertType(String alertTypeDescription) {
        this.alertTypeDescription = alertTypeDescription;
    }
}
