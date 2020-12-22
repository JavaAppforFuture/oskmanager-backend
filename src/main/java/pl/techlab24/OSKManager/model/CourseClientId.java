package pl.techlab24.OSKManager.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CourseClientId implements Serializable { // must not be validated

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "client_id")
    private Long clientId;

    public CourseClientId() {
    }

    public CourseClientId(Long courseId, Long clientId) {
        this.courseId = courseId;
        this.clientId = clientId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CourseClientId)) {
            return false;
        }
        CourseClientId that = (CourseClientId) o;
        return Objects.equals(courseId, that.courseId)
            && Objects.equals(clientId, that.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, clientId);
    }
}
