package pl.techlab24.OSKManager.model.enums;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.Test;

public class RideTypeTest {

    @Test
    void rideTypeValueNormalShouldExist() {
        assertThat(RideType.valueOf("Normal"), is(notNullValue()));
    }

    @Test
    void rideTypeValueAdditionalShouldExist() {
        assertThat(RideType.valueOf("Additional"), is(notNullValue()));
    }
}
