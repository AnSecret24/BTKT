package framework.pages;

import framework.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    @FindBy(css = ".cart_item") private List<WebElement> cartItems;
    @FindBy(id = "checkout") private WebElement checkoutButton;
    @FindBy(css = ".inventory_item_name") private List<WebElement> itemNames;

    public CartPage(WebDriver driver) { super(driver); }

    public int getItemCount() {
        // Xử lý trường hợp giỏ hàng rỗng không throw exception [cite: 590]
        return cartItems.size();
    }

    public List<String> getItemNames() {
        List<String> names = new ArrayList<>();
        for (WebElement e : itemNames) { names.add(getText(e)); }
        return names;
    }

    // Bổ sung method này để fix lỗi Fail ở Bài 4
    public void goToCheckout() {
        waitAndClick(checkoutButton);
    }

    public void clickCheckout() {
        waitAndClick(checkoutButton);
    }
}