package dtm.data;

import org.testng.annotations.DataProvider;

public class GioHangData {
    @DataProvider(name = "du_lieu_gio_hang")
    public Object[][] getCartData() {
        return new Object[][] {
                {"Sauce Labs Backpack", "1"},
                {"Sauce Labs Bike Light", "1"},
                {"Sauce Labs Bolt T-Shirt", "1"}
        };
    }
}