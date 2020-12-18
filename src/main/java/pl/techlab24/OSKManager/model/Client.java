package pl.techlab24.OSKManager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.techlab24.OSKManager.model.enums.DocumentType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Client extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String houseNumber;
    private String apartmentNumber;
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
