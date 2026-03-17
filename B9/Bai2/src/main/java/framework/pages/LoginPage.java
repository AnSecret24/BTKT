package framework.pages;

import framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    // locator
    private By usernameInput = By.id("user-name");
    private By passwordInput = By.id("password");
    private By loginBtn = By.id("login-button");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // action
    public void enterUsername(String username) {
        type(driver.findElement(usernameInput), username);
    }

    public void enterPassword(String password) {
        type(driver.findElement(passwordInput), password);
    }

    public void clickLogin() {
        click(driver.findElement(loginBtn));
    }

    // gộp lại cho nhanh
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
}