package pl.techlab24.OSKManager.model.enums;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.Test;

public class SexTest {
    
    @Test
    void sexValueFemaleShouldExist() {
        assertThat(Sex.valueOf("Female"), is(notNullValue()));
    }

    @Test
    void sexValueMaleShouldExist() {
        assertThat(Sex.valueOf("Male"), is(notNullValue()));
    }

    @Test
    void sexValueOtherShouldExist() {
        assertThat(Sex.valueOf("Other"), is(notNullValue()));
    }
}
