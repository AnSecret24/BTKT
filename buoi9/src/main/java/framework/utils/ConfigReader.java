package framework.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Properties props = new Properties();
    private static ConfigReader instance;

    private ConfigReader() {
        // Đọc biến "env" từ hệ thống, nếu không truyền thì mặc định là "dev" [cite: 492, 659, 660]
        String env = System.getProperty("env", "dev");
        String file = "src/test/resources/config-" + env + ".properties"; //[cite: 493]

        try (FileInputStream fis = new FileInputStream(file)) {
            props.load(fis); //[cite: 494]
            System.out.println("[ConfigReader] Đang dùng môi trường: " + env); //[cite: 495]
        } catch (IOException e) {
            throw new RuntimeException("Không tìm thấy file config: " + file); //[cite: 497]
        }
    }

    // Singleton Pattern để đảm bảo chỉ có 1 bộ đọc config duy nhất [cite: 486, 499, 500]
    public static ConfigReader getInstance() {
        if (instance == null) {
            instance = new ConfigReader();
        }
        return instance;
    }

    public String getBaseUrl() { return props.getProperty("base.url"); } //[cite: 502, 505]

    public int getExplicitWait() {
        return Integer.parseInt(props.getProperty("explicit.wait", "15"));
    } //[cite: 507, 508]
}