package pl.techlab24.OSKManager.model.validation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.techlab24.OSKManager.model.Car;
import pl.techlab24.OSKManager.model.CourseClient;
import pl.techlab24.OSKManager.model.Instructor;
import pl.techlab24.OSKManager.model.Ride;
import pl.techlab24.OSKManager.model.RideDetails;

public class RideValidator extends Validator {

    public static List<String> validate(Ride ride) {
        if (ride == null) {
            return Collections.singletonList("Ride cannot be null.");
        }

        List<String> result = new ArrayList<>();

        addResultOfValidation(result, validateDate(ride.getDate()));

        for (CourseClient courseClient : ride.getCourseClients()) {
            addResultOfValidation(result, validateCourseClient(courseClient));
        }

        addResultOfValidation(result, validateInstructor(ride.getInstructor()));
        addResultOfValidation(result, validateCar(ride.getCar()));

        for (RideDetails rideDetails : ride.getRideDetails()) {
            addResultOfValidation(result, validateRideDetails(rideDetails));
        }

        return result;
    }

    private static String validateDate(LocalDate date) {
        if (date == null) {
            return "Date of ride cannot be null.";
        }
        return null;
    }

    private static List<String> validateCourseClient(CourseClient courseClient) {
        return CourseClientValidator.validate(courseClient);
    }

    private static List<String> validateInstructor(Instructor instructor) {
        return InstructorValidator.validate(instructor);
    }

    private static List<String> validateCar(Car car) {
        return CarValidator.validate(car);
    }

    private static List<String> validateRideDetails(RideDetails rideDetails) {
        return RideDetailsValidator.validate(rideDetails);
    }
}
