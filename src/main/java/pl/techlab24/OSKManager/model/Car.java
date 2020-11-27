package pl.techlab24.OSKManager.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "car")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mark;
    private String model;
    private String plate;
    private Integer productionYear;
    private LocalDate endOfReview;
    private LocalDate endOfInsurance;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Instructor> instructors;

    @OneToMany
    private List<Alert> alert;

    @OneToOne
    private Ride ride;
}
