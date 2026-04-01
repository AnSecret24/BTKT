package dtm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    @FindBy(id = "user-name") private WebElement txtUser; //[cite: 284]
    @FindBy(id = "password") private WebElement txtPass; //[cite: 284]
    @FindBy(id = "login-button") private WebElement btnLogin;//[cite: 284]

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); //[cite: 287]
    }

    public void dangNhap(String user, String pass) {
        txtUser.sendKeys(user); //[cite: 288]
        txtPass.sendKeys(pass);// [cite: 288]
        btnLogin.click(); //[cite: 288]
    }
}