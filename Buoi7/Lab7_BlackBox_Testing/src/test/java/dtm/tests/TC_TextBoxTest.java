package dtm.tests;

import dtm.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_TextBoxTest extends BaseTest {

    @Test(description = "Test form TextBox trên DemoQA")
    public void testTextBoxForm() {
        getDriver().get("https://demoqa.com/text-box");

        // 1. Điền thông tin
        getDriver().findElement(By.id("userName")).sendKeys("Nguyen Van An");
        getDriver().findElement(By.id("userEmail")).sendKeys("an.nguyen@example.com");
        getDriver().findElement(By.id("currentAddress")).sendKeys("123 Street, HCM City");
        getDriver().findElement(By.id("permanentAddress")).sendKeys("456 Road, Hanoi");

        // 2. Submit (Cuộn xuống nếu nút bị che)
        WebElement btnSubmit = getDriver().findElement(By.id("submit"));
        btnSubmit.click();

        // 3. Kiểm tra kết quả hiển thị ở vùng 'output'
        WebElement output = getDriver().findElement(By.id("output"));
        Assert.assertTrue(output.isDisplayed(), "Vùng kết quả không hiển thị!");

        String resultName = getDriver().findElement(By.id("name")).getText();
        Assert.assertTrue(resultName.contains("Nguyen Van An"));

    }
}