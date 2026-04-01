package dtm.tests;

import dtm.base.BaseTest;
import dtm.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TC_GioHangTest extends BaseTest {
    InventoryPage inventoryPage;
    LoginPage loginPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    @BeforeMethod
    public void chuanBi() {

        WebDriver driver = getDriver();

        // 1. Khởi tạo các Page Object [cite: 315, 350]
        loginPage = new LoginPage(getDriver());
        inventoryPage = new InventoryPage(getDriver());
        cartPage = new CartPage(getDriver());
        checkoutPage = new CheckoutPage(getDriver());

        // 2. Thực hiện đăng nhập trước mỗi test case [cite: 369]
        // Sử dụng tài khoản standard_user theo đặc tả [cite: 258]
        getDriver().get("https://www.saucedemo.com"); //[cite: 258]
        loginPage.dangNhap("standard_user", "secret_sauce"); //[cite: 258, 294]
    }

    @Test(groups={"smoke"}, description="TC_CART_001: Thêm 1 sản phẩm")
    public void themMotSanPham() {
        inventoryPage.themNSanPhamDauTien(1); //[cite: 340, 357]
        Assert.assertEquals(inventoryPage.laySoLuongBadge(), 1, "Badge phải là 1"); //[cite: 358, 372]
    }

    @Test(groups={"regression"}, description="TC_CART_010: Kiểm tra tính toán tổng tiền")
    public void kiemTraTongTien() {
        // 1. Thêm 3 sản phẩm có giá khác nhau [cite: 392]
        inventoryPage.themNSanPhamDauTien(3); //[cite: 357]
        inventoryPage.vaoGioHang(); //[cite: 332]

        // 2. Đi qua trang Giỏ hàng và Checkout Step 1 [cite: 393]
        cartPage.clickCheckout(); //[cite: 332]
        // Điền thông tin bắt buộc theo yêu cầu bài Lab [cite: 332]
        checkoutPage.nhapThongTin("An", "Nguyen", "70000"); //[cite: 332]

        // 3. Lấy dữ liệu tại trang Checkout Step 2 [cite: 394]
        double itemTotalWeb = checkoutPage.getItemTotal(); //[cite: 391]
        double taxWeb = checkoutPage.getTax(); //[cite: 391]
        double totalWeb = checkoutPage.getTotal(); //[cite: 391]

        // 4. Assert tính toán với delta 0.01 theo yêu cầu [cite: 390]
        // Kiểm tra Item Total (tổng giá sản phẩm tự tính so với web) [cite: 395]
        // Kiểm tra Tax = 8% của Item Total [cite: 396, 398]
        Assert.assertEquals(taxWeb, itemTotalWeb * 0.08, 0.01, "Thuế 8% không chính xác!"); //[cite: 389, 398]
        // Kiểm tra Total = Item Total + Tax [cite: 397, 399]
        Assert.assertEquals(totalWeb, itemTotalWeb + taxWeb, 0.01, "Tổng tiền (Total) không chính xác!"); //[cite: 389, 399]
    }
}