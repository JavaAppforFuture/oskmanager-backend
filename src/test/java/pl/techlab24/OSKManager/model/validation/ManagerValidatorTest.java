package pl.techlab24.OSKManager.model.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import pl.techlab24.OSKManager.model.Manager;
import pl.techlab24.OSKManager.model.enums.Sex;

class ManagerValidatorTest {

    private Manager correctManager;

    @BeforeEach
    void setup() {
        correctManager = Manager.builder()
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
    void shouldValidateCorrectManager() {
        List<String> resultOfValidation = ManagerValidator.validate(correctManager);
        assertEquals(Collections.emptyList(), resultOfValidation);
    }

    @Test
    void shouldValidateNullManager() {
        List<String> resultOfValidation = ManagerValidator.validate(null);
        assertEquals(Collections.singletonList("Manager cannot be null."), resultOfValidation);
    }

    @Test
    void shouldValidateUserMethodCallValidateMethodFromUserValidatorClass() {
        try (MockedStatic<UserValidator> mockedStatic = mockStatic(UserValidator.class)) {
            // given
            mockedStatic.when(() -> UserValidator.validate(correctManager)).thenReturn(Collections.emptyList());

            // when
            List<String> result = ManagerValidator.validate(correctManager);

            // then
            assertEquals(Collections.emptyList(), result);
            mockedStatic.verify(() -> UserValidator.validate(correctManager));
        }
    }
}
