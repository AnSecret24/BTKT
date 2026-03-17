import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestFailDemo extends BaseTest {

    @Test
    public void testFail() {
        Assert.assertEquals("A", "B"); // cố tình fail
    }
}