package fpoly;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest {

    @Test(groups = {"smoke", "regression"})
    public void testCheckoutSuccess() {
        System.out.println("Checkout Success Test");
        Assert.assertTrue(true);
    }

    @Test(groups = {"regression"})
    public void testCheckoutCancel() {
        System.out.println("Checkout Cancel Test");
        Assert.assertTrue(true);
    }

}