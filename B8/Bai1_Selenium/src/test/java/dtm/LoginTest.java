package dtm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class LoginTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    public void login(String username, String password) {

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }

    @Test
    public void testLoginSuccess() {

        login("standard_user", "secret_sauce");

        wait.until(ExpectedConditions.urlContains("inventory.html"));

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),
                "Đăng nhập thành công nhưng không chuyển trang");
    }

    @Test
    public void testLoginWrongPassword() {

        login("standard_user", "123456");

        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']"))
        );

        Assert.assertTrue(error.isDisplayed(),
                "Không hiển thị lỗi khi sai mật khẩu");
    }

    @Test
    public void testLoginEmptyUsername() {

        login("", "secret_sauce");

        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']"))
        );

        Assert.assertTrue(error.getText().contains("Username is required"),
                "Không hiển thị lỗi Username is required");
    }

    @Test
    public void testLoginEmptyPassword() {

        login("standard_user", "");

        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']"))
        );

        Assert.assertTrue(error.getText().contains("Password is required"),
                "Không hiển thị lỗi Password is required");
    }

    @Test
    public void testLoginLockedUser() {

        login("locked_out_user", "secret_sauce");

        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']"))
        );

        Assert.assertTrue(error.getText().contains("locked out"),
                "Không hiển thị lỗi tài khoản bị khóa");
    }
}