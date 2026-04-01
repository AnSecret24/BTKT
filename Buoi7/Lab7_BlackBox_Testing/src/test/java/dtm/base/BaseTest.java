package dtm.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;

public abstract class BaseTest {
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        WebDriver instance = new ChromeDriver();
        instance.manage().window().maximize();
        instance.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //[cite: 245]
        driver.set(instance);
    }

    public WebDriver getDriver() {
        return driver.get(); //[cite: 252]
    }

    @AfterMethod
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit(); //[cite: 251]
            driver.remove();
        }
    }
}