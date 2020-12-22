package pl.techlab24.OSKManager.model.enums;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.Test;

public class AlertTypeTest {

    @Test
    void alertTypeValueReviewShouldExist() {
        assertThat(AlertType.valueOf("Review"), is(notNullValue()));
    }

    @Test
    void alertTypeValueInsuranceShouldExist() {
        assertThat(AlertType.valueOf("Insurance"), is(notNullValue()));
    }
}
