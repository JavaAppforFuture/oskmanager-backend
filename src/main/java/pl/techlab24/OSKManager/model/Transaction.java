package pl.techlab24.OSKManager.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.techlab24.OSKManager.model.enums.TransactionType;

@Entity
@Table(name = "transaction")
@SuperBuilder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private CourseClient courseClient; // if courseClient is null = withdraw
    // not validated

    private LocalDate date;
    private BigDecimal value;
    private TransactionType transactionType;
    private String description; // not validated
}
