package fpoly;

import factory.DriverFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {

    @BeforeMethod
    public void setup() {

        DriverFactory.initDriver("chrome");

    }

    @Test
    public void testLogin() throws InterruptedException {

        DriverFactory.getDriver().get("https://www.google.com");

        Thread.sleep(3000);

        System.out.println("Login Test Thread: " + Thread.currentThread().getId());

    }

    @AfterMethod
    public void tearDown() {

        DriverFactory.quitDriver();

    }
}