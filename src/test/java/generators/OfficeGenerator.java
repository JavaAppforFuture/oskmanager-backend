package generators;

import pl.techlab24.OSKManager.model.Office;
import pl.techlab24.OSKManager.model.enums.Sex;

public class OfficeGenerator {

    public static Office getRandomOffice() {
        Long id = IdGenerator.getNextId();
        String email = RegexWordGenerator.getRandomRegexWord("[a-z]{3}\\@[a-z]{3}\\.[a-z]{3}");
        String password = WordGenerator.getRandomWord();
        String name = WordGenerator.getRandomWord();
        String secondName = WordGenerator.getRandomWord();
        String surname = WordGenerator.getRandomWord();
        Sex sex = Sex.Female;
        String phoneNumber = RegexWordGenerator.getRandomRegexWord("(\\+48|48)?[5-6][0-9]{8}");
        String role = WordGenerator.getRandomWord();

        return Office.builder()
                .id(id)
                .email(email)
                .password(password)
                .name(name)
                .secondName(secondName)
                .surname(surname)
                .sex(sex)
                .phoneNumber(phoneNumber)
                .role(role)
                .build();
    }
}
