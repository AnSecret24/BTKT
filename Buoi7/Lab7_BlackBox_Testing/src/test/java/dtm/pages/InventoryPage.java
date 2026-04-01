package dtm.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.Select;
import java.util.*;
import java.util.stream.Collectors;

public class InventoryPage {
    private WebDriver driver;

    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    @FindBy(xpath = "//button[text()='Add to cart']")
    private List<WebElement> addToCartButtons;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> itemNames; // Danh sách tên sản phẩm [cite: 361]

    @FindBy(className = "inventory_item_price")
    private List<WebElement> itemPrices; // Danh sách giá sản phẩm [cite: 363]

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void themNSanPhamDauTien(int n) {
        for (int i = 0; i < n && i < addToCartButtons.size(); i++) {
            addToCartButtons.get(i).click(); // Click nút Add to cart [cite: 357]
        }
    }

    public int laySoLuongBadge() {
        try {
            return Integer.parseInt(cartBadge.getText()); // Trả về số trên badge [cite: 358]
        } catch (Exception e) { // Đổi thành Exception chung để bắt cả trường hợp không có text
            return 0;
        }
    }

    public void sortSanPham(String option) {
        new Select(sortDropdown).selectByValue(option); // az, za, lohi, hilo [cite: 360]
    }

    public void vaoGioHang() {
        cartLink.click();
    }

    // --- Bổ sung thêm theo yêu cầu trang 14 của Lab 7 ---

    /** Lấy danh sách tên sản phẩm theo thứ tự hiển thị */
    public List<String> layDanhSachTenSanPham() {
        return itemNames.stream().map(WebElement::getText).collect(Collectors.toList()); //[cite: 361]
    }

    /** Lấy danh sách giá sản phẩm theo thứ tự hiển thị (Kiểu Double) */
    public List<Double> layDanhSachGiaSanPham() {
        return itemPrices.stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .collect(Collectors.toList()); //[cite: 363, 364]
    }
}