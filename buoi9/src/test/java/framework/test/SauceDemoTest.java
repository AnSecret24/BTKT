package framework.test;

import framework.base.BaseTest;
import framework.pages.LoginPage;
import framework.pages.InventoryPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SauceDemoTest extends BaseTest {

    @Test
    public void testLoginAndAddCart_Fluent() {
        LoginPage loginPage = new LoginPage(getDriver());
        getDriver().get("https://www.saucedemo.com");

        // Test Fluent Interface
        int count = loginPage.login("standard_user", "secret_sauce")
                .addFirstItemToCart()
                .getCartItemCount();

        Assert.assertEquals(count, 1, "Số lượng sản phẩm trong giỏ không đúng!");
    }

    @Test
    public void testLoginFail() {
        LoginPage loginPage = new LoginPage(getDriver());
        getDriver().get("https://www.saucedemo.com");

        loginPage.loginExpectingFailure("wrong_user", "wrong_pass");
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Thông báo lỗi không hiển thị!");
    }
}