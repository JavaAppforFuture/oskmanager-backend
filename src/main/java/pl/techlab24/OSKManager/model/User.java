package pl.techlab24.OSKManager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.techlab24.OSKManager.model.enums.Sex;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String email;
    private String password;
    private String name;
    private String secondName;
    private String surname;
    private Sex sex;
    private String phoneNumber;
    private String defaultPassword;

}
