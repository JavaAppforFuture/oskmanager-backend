package pl.techlab24.OSKManager.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder(toBuilder = true)
@Table(name = "course_client")
public class CourseClient {

    @EmbeddedId
    private CourseClientId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("clientId")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("courseId")
    private Course course;

    @OneToMany
    private List<Transaction> transactions;

    private BigDecimal customPrice; // only this variable must be validated

    public CourseClient() {
    }

    public CourseClient(Course course, Client client) {
        this.course = course;
        this.client = client;
        this.id = new CourseClientId(course.getId(), client.getId());
    }

    public CourseClientId getId() {
        return id;
    }

    public void setId(CourseClientId id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public BigDecimal getCustomPrice() {
        return customPrice;
    }

    public void setCustomPrice(BigDecimal customPrice) {
        this.customPrice = customPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CourseClient)) {
            return false;
        }
        CourseClient that = (CourseClient) o;
        return Objects.equals(id, that.id)
            && Objects.equals(client, that.client)
            && Objects.equals(course, that.course)
            && Objects.equals(customPrice, that.customPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, course, customPrice);
    }
}
