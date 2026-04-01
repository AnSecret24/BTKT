package dtm.tests;

import dtm.base.BaseTest;
import dtm.pages.InventoryPage;
import dtm.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TC_TimKiemTest extends BaseTest {
    LoginPage loginPage;
    InventoryPage inventoryPage;

    @BeforeMethod
    public void setup() {
        loginPage = new LoginPage(getDriver());
        inventoryPage = new InventoryPage(getDriver());

        // Đăng nhập để vào trang sản phẩm [cite: 369]
        getDriver().get("https://www.saucedemo.com");
        loginPage.dangNhap("standard_user", "secret_sauce");
    }

    @Test(groups={"regression"}, description="TC_SORT_001: Kiểm tra Sort tên từ A-Z")
    public void testSortTenAZ() {
        // 1. Thực hiện Sort A-Z trên Web [cite: 360]
        inventoryPage.sortSanPham("az");

        // 2. Lấy danh sách tên sản phẩm thực tế từ Web [cite: 361]
        List<String> actualNames = inventoryPage.layDanhSachTenSanPham();

        // 3. Tạo danh sách mong đợi bằng cách tự sắp xếp trong Java
        List<String> expectedNames = actualNames.stream().sorted().collect(Collectors.toList());

        // 4. So sánh
        Assert.assertEquals(actualNames, expectedNames, "Danh sách chưa được sắp xếp đúng A-Z!");
    }

    @Test(groups={"regression"}, description="TC_SORT_003: Kiểm tra Sort giá từ thấp đến cao")
    public void testSortGiaLowToHigh() {
        // 1. Thực hiện Sort Low to High [cite: 360, 386]
        inventoryPage.sortSanPham("lohi");

        // 2. Lấy danh sách giá thực tế từ Web [cite: 363, 364]
        List<Double> actualPrices = inventoryPage.layDanhSachGiaSanPham();

        // 3. Tạo danh sách mong đợi
        List<Double> expectedPrices = actualPrices.stream().sorted().collect(Collectors.toList());

        // 4. So sánh
        Assert.assertEquals(actualPrices, expectedPrices, "Giá chưa được sắp xếp tăng dần!");
    }
}