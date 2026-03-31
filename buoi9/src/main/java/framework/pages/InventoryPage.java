package framework.pages;

import framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class InventoryPage extends BasePage {
    @FindBy(css = ".inventory_list") private WebElement inventoryList;
    @FindBy(css = ".shopping_cart_badge") private WebElement cartBadge;
    @FindBy(css = ".btn_inventory") private List<WebElement> addToCartButtons;

    public InventoryPage(WebDriver driver) { super(driver); }

    public boolean isLoaded() { return isElementVisible(By.cssSelector(".inventory_list")); }

    public InventoryPage addFirstItemToCart() {
        // Đợi trang load xong hoàn toàn trước
        waitForPageLoad();

        // Kiểm tra nếu list không rỗng mới click
        if (!addToCartButtons.isEmpty()) {
            waitAndClick(addToCartButtons.get(0));
        } else {
            throw new RuntimeException("Không tìm thấy nút Add to Cart nào trên trang!");
        }
        return this;
    }

    public int getCartItemCount() {
        try {
            return Integer.parseInt(getText(cartBadge));
        } catch (Exception e) {
            return 0; // Giỏ hàng rỗng [cite: 274]
        }
    }

    public CartPage goToCart() {
        waitAndClick(driver.findElement(By.className("shopping_cart_link")));
        return new CartPage(driver);
    }
}