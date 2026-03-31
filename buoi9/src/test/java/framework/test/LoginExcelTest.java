package framework.test;

import framework.base.BaseTest;
import framework.pages.LoginPage;
import framework.utils.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginExcelTest extends BaseTest {

    @DataProvider(name = "excelData")
    public Object[][] getLoginData() {
        String path = "src/test/resources/testdata/login_data.xlsx";
        return ExcelReader.getData(path, "SmokeCases"); // Đọc từ sheet SmokeCases [cite: 412]
    }

    @Test(dataProvider = "excelData")
    public void testLoginFromExcel(String user, String pass, String expectedUrl, String desc) {
        System.out.println("Đang test case: " + desc);
        getDriver().get("https://www.saucedemo.com");

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(user, pass);

        Assert.assertTrue(getDriver().getCurrentUrl().contains(expectedUrl));
    }
}