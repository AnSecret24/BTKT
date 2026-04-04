package dtm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;

public class LoginTest {
    WebDriverWait wait;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // Khởi tạo driver thông qua Factory [cite: 189]
        DriverFactory.initDriver();
        // Lấy driver của luồng hiện tại để thiết lập Wait [cite: 202]
        wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));
        DriverFactory.getDriver().get("https://www.saucedemo.com");
    }

    @Test(groups = {"smoke", "regression"}, description = "TC05: Dang nhap thanh cong")
    public void testLoginSuccess() {
        DriverFactory.getDriver().findElement(By.id("user-name")).sendKeys("standard_user");
        DriverFactory.getDriver().findElement(By.id("password")).sendKeys("secret_sauce");
        DriverFactory.getDriver().findElement(By.id("login-button")).click();

        wait.until(ExpectedConditions.urlContains("inventory.html"));
        Assert.assertTrue(DriverFactory.getDriver().getCurrentUrl().contains("inventory.html"), "Loi chuyển trang!");
    }

    @Test(groups = {"regression"}, description = "TC06: Nhap sai mat khau")
    public void testLoginWrongPassword() {
        DriverFactory.getDriver().findElement(By.id("user-name")).sendKeys("standard_user");
        DriverFactory.getDriver().findElement(By.id("password")).sendKeys("wrong_password");
        DriverFactory.getDriver().findElement(By.id("login-button")).click();

        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']")));
        Assert.assertTrue(errorMsg.getText().contains("Username and password do not match"));
    }

    @Test(groups = {"regression"}, description = "TC07: Bo trong Username")
    public void testLoginEmptyUsername() {
        DriverFactory.getDriver().findElement(By.id("password")).sendKeys("secret_sauce");
        DriverFactory.getDriver().findElement(By.id("login-button")).click();

        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']")));
        Assert.assertEquals(errorMsg.getText(), "Epic sadface: Username is required");
    }

    @Test(groups = {"regression"}, description = "TC08: Bo trong Password")
    public void testLoginEmptyPassword() {
        DriverFactory.getDriver().findElement(By.id("user-name")).sendKeys("standard_user");
        DriverFactory.getDriver().findElement(By.id("login-button")).click();

        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']")));
        Assert.assertEquals(errorMsg.getText(), "Epic sadface: Password is required");
    }

    @Test(groups = {"regression"}, description = "TC09: Dung locked_out_user")
    public void testLoginLockedUser() {
        DriverFactory.getDriver().findElement(By.id("user-name")).sendKeys("locked_out_user");
        DriverFactory.getDriver().findElement(By.id("password")).sendKeys("secret_sauce");
        DriverFactory.getDriver().findElement(By.id("login-button")).click();

        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']")));
        Assert.assertTrue(errorMsg.getText().contains("Sorry, this user has been locked out"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // Đóng driver của luồng hiện tại và dọn dẹp ThreadLocal [cite: 203, 206]
        DriverFactory.quitDriver();
    }
}