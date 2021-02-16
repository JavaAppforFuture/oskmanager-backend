package pl.techlab24.OSKManager.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.techlab24.OSKManager.model.enums.DocumentType;

@Entity
@Data
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Client extends User {

    private String street;
    private String houseNumber;
    private String apartmentNumber; // not validated
    private String postcode;
    private String city;
    private String pesel; // date of birth accepted
    private DocumentType documentType;
    private String documentNumber;

    @OneToMany(mappedBy = "client")
    private List<DriverCandidateProfile> driverCandidateProfile; // not validated

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client", orphanRemoval = true)
    private List<CourseClient> courseClients = new ArrayList<>(); // not validated
}
