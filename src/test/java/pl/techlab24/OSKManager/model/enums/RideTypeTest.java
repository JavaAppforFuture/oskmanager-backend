package pl.techlab24.OSKManager.model.enums;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

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
