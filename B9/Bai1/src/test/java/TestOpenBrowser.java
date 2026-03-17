import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestOpenBrowser {

    @Test
    public void openGoogle() {

        WebDriverManager.chromedriver().setup(); // tự tải driver

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.google.com");

        try {
            Thread.sleep(3000); // xem trình duyệt 3s
        } catch (Exception e) {}

        driver.quit();
    }
}