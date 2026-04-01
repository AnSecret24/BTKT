package dtm.tests;

import dtm.base.BaseTest;
import dtm.data.DangNhapData;
import dtm.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_DangNhapTest extends BaseTest {

    @Test(
            dataProvider = "du_lieu_dang_nhap",
            dataProviderClass = DangNhapData.class,
            description = "Kiểm thử đăng nhập Saucedemo với DataProvider"
    )
    public void kiemThuDangNhap(String user, String pass, String expected, String moTa) { //[cite: 312-314]
        // 1. Mở trang web
        getDriver().get("https://www.saucedemo.com");

        // 2. Khởi tạo Page Object và thực hiện đăng nhập
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.dangNhap(user, pass); //[cite: 315-316]

        // 3. Kiểm tra kết quả (Assert) dựa trên mong đợi [cite: 317-319]
        if (expected.equals("THÀNH CONG")) {
            String actualUrl = getDriver().getCurrentUrl();
            Assert.assertTrue(actualUrl.contains("inventory.html"), "Lỗi: " + moTa);
        } else {
            // Bạn có thể viết thêm hàm layThongBaoLoi() bên LoginPage để Assert chi tiết ở đây
            Assert.assertTrue(getDriver().getPageSource().contains("Epic sadface"), "Lỗi: " + moTa);
        }
    }
}