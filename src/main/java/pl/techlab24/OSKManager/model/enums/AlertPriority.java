package pl.techlab24.OSKManager.model.enums;

public enum AlertPriority {

    Normal("Normal"),
    Urgent("Urgent"),
    Critical("Critical"),
    SuperCritical("SuperCritical");

    private final String alertPriorityDescription;

    AlertPriority(String alertPriorityDescription) {
        this.alertPriorityDescription = alertPriorityDescription;
    }
}
