package pl.techlab24.OSKManager.model.enums;

public enum RideType {

    Normal("Normal"),
    Additional("Additional");

    private final String rideTypeDescription;

    RideType(String rideTypeDescription) {
        this.rideTypeDescription = rideTypeDescription;
    }
}
