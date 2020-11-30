package pl.techlab24.OSKManager.model.enums;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class AlertPriorityTest {


    @Test
    void alertPriorityValueNormalShouldExist() {
        assertThat(AlertPriority.valueOf("Normal"), is(notNullValue()));
    }

    @Test
    void alertPriorityValueUrgentShouldExist() {
        assertThat(AlertPriority.valueOf("Urgent"), is(notNullValue()));
    }

    @Test
    void alertPriorityValueCriticalShouldExist() {
        assertThat(AlertPriority.valueOf("Critical"), is(notNullValue()));
    }

    @Test
    void alertPriorityValueSuperCriticalShouldExist() {
        assertThat(AlertPriority.valueOf("SuperCritical"), is(notNullValue()));
    }
}
