package framework.test;

import framework.base.BaseTest;
import framework.models.UserData;
import framework.pages.LoginPage;
import framework.utils.JsonReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;

public class UserLoginJsonTest extends BaseTest {

    @DataProvider(name = "jsonUsers")
    public Object[][] getUsersFromJson() throws IOException {
        // Đọc danh sách user từ file JSON [cite: 619]
        List<UserData> users = JsonReader.readUsers("src/test/resources/testdata/users.json");

        // Dùng Stream để chuyển List thành Object[][] cho DataProvider [cite: 458, 459]
        return users.stream()
                .map(u -> new Object[]{u.username, u.password, u.expectSuccess, u.description})
                .toArray(Object[][]::new);
    }

    @Test(dataProvider = "jsonUsers", description = "Kiểm tra đăng nhập dùng dữ liệu JSON")
    public void testLoginWithJson(String username, String password, boolean expectSuccess, String desc) {
        System.out.println("Đang thực hiện: " + desc);
        getDriver().get("https://www.saucedemo.com");

        LoginPage loginPage = new LoginPage(getDriver());

        if (expectSuccess) {
            // Case đăng nhập thành công [cite: 620]
            loginPage.login(username, password);
            Assert.assertTrue(getDriver().getCurrentUrl().contains("inventory.html"), "Lỗi: " + desc);
        } else {
            // Case đăng nhập thất bại [cite: 620]
            loginPage.loginExpectingFailure(username, password);
            Assert.assertTrue(loginPage.isErrorDisplayed(), "Lỗi: " + desc);
        }
    }
}