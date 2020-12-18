package pl.techlab24.OSKManager.model;

import pl.techlab24.OSKManager.model.enums.Sex;

import javax.persistence.*;

@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
@Inheritance
public abstract class User {

    @Id
    private Long id;

    private String email;
    private String password;
    private String name;
    private String secondName;
    private String surname;
    private Sex sex;
    private String phoneNumber;
    private String defaultPassword;
}
