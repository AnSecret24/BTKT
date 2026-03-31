package framework.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    public static void capture(WebDriver driver, String name) {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // Lưu vào target/screenshots/ tên file gồm {testName}_{timestamp} [cite: 571, 572]
            FileUtils.copyFile(src, new File("target/screenshots/" + name + "_" + timestamp + ".png"));
        } catch (Exception e) {
            System.out.println("Lỗi chụp ảnh: " + e.getMessage());
        }
    }
}