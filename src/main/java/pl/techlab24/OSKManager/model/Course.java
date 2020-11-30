package pl.techlab24.OSKManager.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "course")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseNumber;
    private LocalDate startDate;
    private LocalDate endDate; // not validated (set when all rides done)

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Category category;

    private BigDecimal defaultPrice;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course", orphanRemoval = true)
    private List<CourseClient> courseClients = new ArrayList<>(); // not validated

    public void addClient(Client client) {
        CourseClient courseClient = new CourseClient(this, client);
        courseClients.add(courseClient);
        client.getCourseClients().add(courseClient);
    }

    public void removeClient(Client client) {
        for (Iterator<CourseClient> iterator = courseClients.iterator(); iterator.hasNext(); ) {
            CourseClient courseClient = iterator.next();
            if (courseClient.getCourse().equals(this) && courseClient.getClient().equals(client)) {
                iterator.remove();
                courseClient.getClient().getCourseClients().remove(courseClient);
                courseClient.setClient(null);
                courseClient.setCourse(null);
            }
        }
    }
}
