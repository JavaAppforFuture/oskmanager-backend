package pl.techlab24.OSKManager.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.techlab24.OSKManager.model.enums.AlertPriority;
import pl.techlab24.OSKManager.model.enums.AlertType;

@Entity
@Table(name = "alert")
@SuperBuilder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private LocalDate date;
    private String description; // validation not needed because field is not mandatory
    private AlertType alertType;
    private AlertPriority alertPriority;
}
