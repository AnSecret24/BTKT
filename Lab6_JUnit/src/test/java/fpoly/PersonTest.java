package fpoly;

import com.quanlyduadon.fpoly.Person;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.fail;

public class PersonTest {

    // ========= CÁCH 2: ExpectedException Rule =========
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    // ========= CÁCH 1: @Test(expected) =========
    @Test(expected = IllegalArgumentException.class)
    public void testAgeNegative_expected() {
        new Person("Nam", -1);
    }

    // ========= CÁCH 2: ExpectedException Rule =========
    @Test
    public void testAgeNegative_rule() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Age must be >= 0");

        new Person("Lan", -5);
    }

    // ========= CÁCH 3: try-catch =========
    @Test
    public void testAgeNegative_tryCatch() {
        try {
            new Person("Minh", -10);
            fail("Exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }
}
