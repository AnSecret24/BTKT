package dtm;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
    // ThreadLocal giúp mỗi luồng (thread) có một biến driver riêng biệt [cite: 187, 188]
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static void initDriver() {
        WebDriverManager.chromedriver().setup(); // Thiết lập ChromeDriver [cite: 196]
        WebDriver driver = new ChromeDriver(); // Khởi tạo trình duyệt [cite: 197]
        driver.manage().window().maximize(); // Phóng to màn hình [cite: 200]
        tlDriver.set(driver); // Lưu driver vào ThreadLocal của luồng hiện tại [cite: 201]
    }

    public static WebDriver getDriver() {
        return tlDriver.get(); // Lấy driver tương ứng với luồng đang chạy [cite: 202]
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit(); // Đóng trình duyệt [cite: 205]
            tlDriver.remove(); // Xóa sạch để tránh rò rỉ bộ nhớ (rất quan trọng) [cite: 206]
        }
    }
}