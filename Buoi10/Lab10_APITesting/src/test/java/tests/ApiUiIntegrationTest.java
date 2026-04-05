package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import static io.restassured.RestAssured.given;

public class ApiUiIntegrationTest extends ApiBaseTest {
    private WebDriver driver;

    @BeforeMethod
    public void checkPrecondition() {
        // Bước 1[cite: 590]: Gọi API xác nhận server sống
        int statusCode = given(requestSpec).when().get("/users").getStatusCode();
        if (statusCode != 200) {
            throw new SkipException("API chet - Bo qua UI test"); //[cite: 591]
        }

        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(description = "Bai 6: Ket hop API setup va UI Verify")
    public void testSauceDemoIntegration() {
        // 1.[cite: 579]: Goi API lay token de xac nhan tai khoan hop le
        String token = given(requestSpec)
                .body("{\"email\":\"eve.holt@reqres.in\",\"password\":\"cityslicka\"}")
                .post("/login").then().extract().path("token");
        System.out.println("[API] Token hop le: " + token);

        // 2.[cite: 582]: Thuc hien luong UI
        driver.get("https://www.saucedemo.com/");

        // Dien form
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // 3.[cite: 584]: Verify ket qua qua UI
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("inventory.html"));

        String title = driver.findElement(By.className("title")).getText();
        Assert.assertEquals(title, "Products", "Loi: Khong vao duoc trang Inventory!"); //[cite: 395-396]
        System.out.println("[UI] Verify thanh cong: Dang o trang Inventory.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}