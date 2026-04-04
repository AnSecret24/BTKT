package dtm;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;

public class GridTest {

    @Test(description = "Chạy Test trên Selenium Grid")
    public void testWithGrid() throws MalformedURLException {
        // 1. Thiết lập Options (Trình duyệt mong muốn)
        ChromeOptions options = new ChromeOptions();

        // 2. Kết nối tới Hub của Grid (localhost:4444)
        URL gridUrl = new URL("http://localhost:4444");
        WebDriver driver = new RemoteWebDriver(gridUrl, options);

        // 3. Thực hiện kịch bản Test
        driver.get("https://www.saucedemo.com");
        String title = driver.getTitle();
        Assert.assertEquals(title, "Swag Labs");

        // 4. Đóng trình duyệt
        driver.quit();
    }
}