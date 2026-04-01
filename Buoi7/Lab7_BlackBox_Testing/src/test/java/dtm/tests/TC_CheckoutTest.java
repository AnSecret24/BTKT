package dtm.tests;

import dtm.base.BaseTest;
import dtm.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC_CheckoutTest extends BaseTest {
    // Khai báo các trang
    LoginPage loginPage;
    InventoryPage inventoryPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        // Lấy driver từ ThreadLocal trong BaseTest [cite: 252]
        WebDriver driver = getDriver();

        // KHỞI TẠO CÁC PAGE Ở ĐÂY (Để tránh lỗi NullPointerException)
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);

        // Đăng nhập chuẩn bị cho luồng Checkout [cite: 369]
        driver.get("https://www.saucedemo.com");
        loginPage.dangNhap("standard_user", "secret_sauce");
    }

    @Test(groups={"regression"}, description="Kiểm tra tính toán hóa đơn")
    public void kiemTraTinhToanHoaDon() {
        // 1. Thêm 3 sản phẩm vào giỏ [cite: 392]
        inventoryPage.themNSanPhamDauTien(3);
        inventoryPage.vaoGioHang();

        // 2. Thực hiện Checkout Step 1
        cartPage.clickCheckout();
        checkoutPage.nhapThongTin("An", "Nguyen", "70000");

        // 3. Lấy dữ liệu tính toán tại Step 2 [cite: 394]
        double itemTotal = checkoutPage.getItemTotal();
        double tax = checkoutPage.getTax();
        double total = checkoutPage.getTotal();

        // 4. Assert logic tính toán (Sai số 0.01) [cite: 390]
        Assert.assertEquals(tax, itemTotal * 0.08, 0.01, "Lỗi: Thuế 8% không khớp!"); //[cite: 398]
        Assert.assertEquals(total, itemTotal + tax, 0.01, "Lỗi: Tổng tiền không khớp!"); //[cite: 399]

        // 5. Kết thúc đơn hàng
        checkoutPage.clickFinish();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("checkout-complete"));
    }
}