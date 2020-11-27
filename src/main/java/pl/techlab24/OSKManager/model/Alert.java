package pl.techlab24.OSKManager.model;

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
import pl.techlab24.OSKManager.model.enums.AlertPriority;
import pl.techlab24.OSKManager.model.enums.AlertType;

@Entity
@Table(name = "alert")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private LocalDate date;
    private String description;
    private AlertType alertType;

    @ManyToOne
    private Car car;

    private AlertPriority alertPriority;
}
