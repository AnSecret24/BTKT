package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TextBoxPage {
    private WebDriver driver;
    private WebDriverWait wait;


    @FindBy(id = "userName") private WebElement nameField;
    @FindBy(id = "userEmail") private WebElement emailField;
    @FindBy(id = "currentAddress") private WebElement addressField;
    @FindBy(id = "submit") private WebElement submitBtn;
    @FindBy(id = "output") private WebElement outputSection;

    public TextBoxPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void fillAndSubmit(String name, String email, String address) {
        nameField.clear();
        nameField.sendKeys(name);
        emailField.clear();
        emailField.sendKeys(email);
        addressField.clear();
        addressField.sendKeys(address);


        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitBtn);

        try {
            submitBtn.click();
        } catch (Exception e) {

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitBtn);
        }
    }

    public boolean isOutputDisplayed() {
        try {

            wait.until(ExpectedConditions.visibilityOf(outputSection));
            return outputSection.isDisplayed() && !outputSection.getText().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}