package dtm.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class CheckoutPage {
    private WebDriver driver;

    // --- Các thành phần của Step 1 (Thông tin khách hàng) ---
    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement zipCodeField;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    @FindBy(css = "h3[data-test='error']")
    private WebElement errorMessage;

    // --- Các thành phần của Step 2 (Tổng quan & Tính toán) ---
    @FindBy(className = "summary_subtotal_label")
    private WebElement itemTotalLabel;

    @FindBy(className = "summary_tax_label")
    private WebElement taxLabel;

    @FindBy(className = "summary_total_label")
    private WebElement totalLabel;

    @FindBy(id = "finish")
    private WebElement finishButton;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // --- Các hàm xử lý Step 1 ---
    public void nhapThongTin(String fname, String lname, String zip) {
        firstNameField.sendKeys(fname);
        lastNameField.sendKeys(lname);
        zipCodeField.sendKeys(zip);
        continueButton.click();
    }

    public String layLoiHienThi() {
        return errorMessage.getText();
    }

    // --- Các hàm xử lý Step 2 (Tính toán) ---
    private double parsePrice(String text) {
        return Double.parseDouble(text.replaceAll("[^0-9.]", ""));
    }

    public double getItemTotal() { return parsePrice(itemTotalLabel.getText()); }
    public double getTax() { return parsePrice(taxLabel.getText()); }
    public double getTotal() { return parsePrice(totalLabel.getText()); }

    public void clickFinish() {
        finishButton.click();
    }
}