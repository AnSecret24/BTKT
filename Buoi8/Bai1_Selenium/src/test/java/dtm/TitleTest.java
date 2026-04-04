package dtm;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

public class TitleTest {

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get("https://www.saucedemo.com");
    }

    @Test(groups = {"smoke", "regression"}, description = "Kiểm thử tiêu đề trang chủ")
    public void testTitle() {
        String expectedTitle = "Swag Labs";
        String actualTitle = DriverFactory.getDriver().getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Tiêu đề trang không đúng!");
    }

    @Test(groups = {"regression"}, description = "Kiểm thử URL trang chủ")
    public void testURL() {
        String actualUrl = DriverFactory.getDriver().getCurrentUrl();
        Assert.assertTrue(actualUrl.contains("saucedemo"), "URL không hợp lệ!");
    }

    @Test(groups = {"regression"}, description = "Kiểm thử nguồn trang")
    public void testPageSource() {
        String source = DriverFactory.getDriver().getPageSource();
        Assert.assertTrue(source.contains("standard_user"), "Nguồn trang thiếu user mẫu!");
    }

    @Test(groups = {"regression"}, description = "Kiểm thử Form hiển thị")
    public void testLoginFormDisplay() {
        boolean userDisplayed = DriverFactory.getDriver().findElement(By.id("user-name")).isDisplayed();
        boolean passDisplayed = DriverFactory.getDriver().findElement(By.id("password")).isDisplayed();
        Assert.assertTrue(userDisplayed && passDisplayed, "Form không hiển thị!");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}