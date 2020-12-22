package pl.techlab24.OSKManager.model.enums;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.Test;

public class DocumentTypeTest {

    @Test
    void documentTypePhotoIdShouldExist() {
        assertThat(DocumentType.valueOf("PhotoId"), is(notNullValue()));
    }

    @Test
    void documentTypePassportShouldExist() {
        assertThat(DocumentType.valueOf("Passport"), is(notNullValue()));
    }

    @Test
    void documentTypeOtherShouldExist() {
        assertThat(DocumentType.valueOf("Other"), is(notNullValue()));
    }
}
