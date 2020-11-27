package pl.techlab24.OSKManager.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "instructor", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Instructor extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Category> categories;

    private BigDecimal standardPaymentRate;
    private BigDecimal additionalPaymentRate;
    private String instructorNumber;
    private LocalDate licenceExpireDate;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Car> cars;

    @OneToOne
    private Ride ride;
}
