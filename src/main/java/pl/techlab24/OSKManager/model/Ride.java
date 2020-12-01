package pl.techlab24.OSKManager.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ride")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @OneToMany
    private List<CourseClient> courseClients;

    @ManyToOne
    private Instructor instructor;

    @ManyToOne
    private Car car;

    @OneToMany
    private List<RideDetails> rideDetails;
}
