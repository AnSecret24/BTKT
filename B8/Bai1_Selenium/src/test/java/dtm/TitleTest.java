package dtm;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TitleTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com");
    }

    @Test(description = "Kiem thu tieu de trang chu")
    public void testTitle() {

        String expectedTitle = "Swag Labs";

        String actualTitle = driver.getTitle();

        Assert.assertEquals(actualTitle, expectedTitle, "Tieu de trang khong dung!");
    }

    @Test(description = "Kiem thu URL trang chu")
    public void testURL() {

        String actualUrl = driver.getCurrentUrl();

        Assert.assertTrue(actualUrl.contains("saucedemo"), "URL khong hop le!");
    }

    // TEST TỰ THIẾT KẾ 1
    @Test(description = "Kiem thu page source")
    public void testPageSource() {

        String pageSource = driver.getPageSource();

        Assert.assertTrue(pageSource.contains("login-button"),
                "Khong tim thay login button trong page source!");
    }

    // TEST TỰ THIẾT KẾ 2
    @Test(description = "Kiem thu form dang nhap hien thi")
    public void testLoginFormDisplayed() {

        boolean username = driver.findElement(By.id("user-name")).isDisplayed();

        boolean password = driver.findElement(By.id("password")).isDisplayed();

        Assert.assertTrue(username && password, "Form login khong hien thi!");
    }

    @AfterMethod
    public void tearDown() {

        driver.quit();
    }
}