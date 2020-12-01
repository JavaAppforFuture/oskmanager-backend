package pl.techlab24.OSKManager.model;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.techlab24.OSKManager.model.enums.RideType;

@Entity
@Table(name = "ride_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private RideType rideType;
    private BigDecimal duration;

    @ManyToOne
    private Ride ride; // not validated
}
