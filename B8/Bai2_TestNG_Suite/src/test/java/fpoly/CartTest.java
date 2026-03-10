package fpoly;

import factory.DriverFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest {

    @BeforeMethod
    public void setup() {

        DriverFactory.initDriver("chrome");

    }

    @Test
    public void testCart() throws InterruptedException {

        DriverFactory.getDriver().get("https://www.wikipedia.org");

        Thread.sleep(3000);

        System.out.println("Cart Test Thread: " + Thread.currentThread().getId());

    }

    @AfterMethod
    public void tearDown() {

        DriverFactory.quitDriver();

    }
}