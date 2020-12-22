package pl.techlab24.OSKManager.model.validation;

import pl.techlab24.OSKManager.model.CourseClient;
import pl.techlab24.OSKManager.model.Ride;
import pl.techlab24.OSKManager.model.RideDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RideValidator extends Validator {

    public static List<String> validate(Ride ride) {
        if (ride == null) {
            return Collections.singletonList("Ride cannot be null.");
        }

        List<String> result = new ArrayList<>();

        for (RideDetails rideDetails : ride.getRideDetails()) {
            addResultOfValidation(result, RideDetailsValidatior.validate(rideDetails));
        }

        for (CourseClient courseClient : ride.getCourseClients()) {
            addResultOfValidation(result, CourseClientValidator.validate(courseClient));
        }

        addResultOfValidation(result, InstructorValidator.validate(ride.getInstructor()));
        addResultOfValidation(result, CarValidator.validate(ride.getCar()));
        addResultOfValidation(result, validateDate(ride.getDate()));

        return result;
    }

    private static String validateDate(LocalDate date) {
        if (date == null) {
            return "Date of ride cannot be null.";
        }

        return null;
    }


}
