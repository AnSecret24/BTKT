package dtm;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.TextBoxPage;

public class TextBoxWhiteBoxTest {
    WebDriver driver;
    TextBoxPage textBoxPage;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/text-box");
        textBoxPage = new TextBoxPage(driver);
    }

    @Test(priority = 1, description = "Path 1: Full valid data -> Show Output")
    public void testValidSubmission() {

        textBoxPage.fillAndSubmit("Nguyen Van An", "an.test@gmail.com", "123 Street, HCM");

        Assert.assertTrue(textBoxPage.isOutputDisplayed(),
                "Lỗi: Đã nhập đúng thông tin nhưng vùng kết quả không hiển thị!");
    }

    @Test(priority = 2, description = "Path 2: Invalid Email -> No Output")
    public void testInvalidEmail() {
        driver.navigate().refresh();
        textBoxPage.fillAndSubmit("An", "an@gmail", "HCM");


        try { Thread.sleep(1000); } catch (InterruptedException e) {}

        Assert.assertFalse(textBoxPage.isOutputDisplayed(),
                "Lỗi: Email sai định dạng nhưng vùng kết quả cũ vẫn hiển thị!");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}