package pl.techlab24.OSKManager.model.validation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.techlab24.OSKManager.model.RideDetails;
import pl.techlab24.OSKManager.model.enums.RideType;

public class RideDetailsValidator extends Validator {

    public static List<String> validate(RideDetails rideDetails) {
        if (rideDetails == null) {
            return Collections.singletonList("Ride details cannot be null.");
        }

        List<String> result = new ArrayList<>();

        addResultOfValidation(result, validateRideType(rideDetails.getRideType()));
        addResultOfValidation(result, validateRideDuration(rideDetails.getDuration()));

        return result;
    }

    private static String validateRideType(RideType rideType) {
        if (rideType == null) {
            return "Ride type cannot be null.";
        }
        return null;
    }

    private static String validateRideDuration(BigDecimal duration) {
        if (duration == null) {
            return "Ride duration cannot be null.";
        }
        if (duration.compareTo(BigDecimal.ZERO) < 0) {
            return "Ride duration cannot be lower than 0.";
        }
        return null;
    }
}
