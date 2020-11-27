package pl.techlab24.OSKManager.model;

import pl.techlab24.OSKManager.model.enums.DocumentType;
import pl.techlab24.OSKManager.model.enums.Sex;

public class User {

    private String email;
    private String password;
    private String name;
    private String secondName;
    private String surname;
    private Sex sex;
    private String street;
    private String houseNumber;
    private String apartmentNumber;
    private String postcode;
    private String city;
    private String pesel; // date of birth accepted
    private DocumentType documentType;
    private String documentNumber;
    private String phoneNumber;
    private String defaultPassword;
}
