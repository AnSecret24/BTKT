package framework.test;

import framework.base.BaseTest;
import framework.pages.LoginPage;
import framework.pages.CartPage;
import framework.utils.TestDataFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor; // Thêm dòng này
import org.openqa.selenium.WebElement;       // Thêm dòng này
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class CheckoutTest extends BaseTest {

    @Test(description = "Kiểm tra Checkout với Faker - Dữ liệu ngẫu nhiên")
    public void testCheckoutWithFaker() {
        getDriver().get("https://www.saucedemo.com");

        // 1. Login và đi đến Cart sử dụng Fluent Interface
        new LoginPage(getDriver())
                .login("standard_user", "secret_sauce")
                .addFirstItemToCart()
                .goToCart();

        // 2. Tại CartPage, bấm Checkout
        new CartPage(getDriver()).clickCheckout();

        // 3. Lấy dữ liệu ngẫu nhiên từ Faker
        String fName = TestDataFactory.randomFirstName();
        String lName = TestDataFactory.randomLastName();
        String zip = TestDataFactory.randomPostalCode();

        System.out.println("Thực hiện Checkout với: " + fName + " " + lName + " (" + zip + ")");

        // 4. Điền thông tin
        getDriver().findElement(By.id("first-name")).sendKeys(fName);
        getDriver().findElement(By.id("last-name")).sendKeys(lName);
        getDriver().findElement(By.id("postal-code")).sendKeys(zip);

        // SỬA ĐOẠN NÀY: Dùng JavaScript để click nút Continue chắc chắn hơn
        WebElement continueBtn = getDriver().findElement(By.id("continue"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", continueBtn);

        // 5. Đợi URL thay đổi
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10)); // Tăng lên 10s cho chắc
        wait.until(ExpectedConditions.urlContains("checkout-step-two"));

        // 6. Kiểm tra đã đến trang Overview chưa
        Assert.assertTrue(getDriver().getCurrentUrl().contains("checkout-step-two"), "Không đến được trang Overview!");
    }
}