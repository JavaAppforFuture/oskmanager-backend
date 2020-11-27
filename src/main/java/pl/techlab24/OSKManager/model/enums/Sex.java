package pl.techlab24.OSKManager.model.enums;

public enum Sex {

    Female("Female"),
    Male("Male"),
    Other("Other");

    private final String sexDescription;

    Sex(String sexDescription) {
        this.sexDescription = sexDescription;
    }
}
