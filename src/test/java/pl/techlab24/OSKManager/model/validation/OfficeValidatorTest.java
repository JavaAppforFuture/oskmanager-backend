package pl.techlab24.OSKManager.model.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import pl.techlab24.OSKManager.model.Office;
import pl.techlab24.OSKManager.model.enums.Sex;

class OfficeValidatorTest {

    private Office correctOffice;

    @BeforeEach
    void setup() {
        correctOffice = Office.builder()
            .id(1L)
            .email("test@test.com")
            .password("test_password_*#Eghicnshd765()")
            .name("Test Name")
            .secondName("Test Second Name")
            .surname("Test Surname")
            .sex(Sex.Other)
            .phoneNumber("+48123456789")
            .defaultPassword(null)
            .role("Test Role")
            .build();
    }

    @Test
    void shouldValidateCorrectOffice() {
        // when
        List<String> resultOfValidation = OfficeValidator.validate(correctOffice);

        // then
        assertEquals(Collections.emptyList(), resultOfValidation);
    }

    @Test
    void shouldValidateNullOffice() {
        // when
        List<String> resultOfValidation = OfficeValidator.validate(null);

        // then
        assertEquals(Collections.singletonList("Office cannot be null."), resultOfValidation);
    }

    @Test
    void shouldValidateUserMethodCallValidateMethodFromUserValidatorClass() {
        try (MockedStatic<UserValidator> mockedStatic = mockStatic(UserValidator.class)) {
            // given
            mockedStatic.when(() -> UserValidator.validate(correctOffice)).thenReturn(Collections.emptyList());

            // when
            List<String> result = OfficeValidator.validate(correctOffice);

            // then
            assertEquals(Collections.emptyList(), result);
            mockedStatic.verify(() -> UserValidator.validate(correctOffice));
        }
    }
}
