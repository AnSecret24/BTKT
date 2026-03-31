package framework.utils;

import com.github.javafaker.Faker;
import java.util.Locale;

public class TestDataFactory {
    // Sử dụng Locale "vi" để sinh dữ liệu tiếng Việt nếu muốn [cite: 629]
    private static final Faker faker = new Faker(new Locale("vi"));

    public static String randomFirstName() { return faker.name().firstName(); } //[cite: 630, 631]
    public static String randomLastName() { return faker.name().lastName(); } //[cite: 632, 633]
    public static String randomPostalCode() { return faker.number().digits(5); } //[cite: 634]
}