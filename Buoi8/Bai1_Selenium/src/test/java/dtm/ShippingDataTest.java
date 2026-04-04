package dtm;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ShippingDataTest {

    // Hàm logic tính phí ship (An có thể gọi từ class cũ hoặc viết lại ở đây)
    public double tinhPhiShip(double trongLuong, String vung, boolean laMember) {
        if (trongLuong <= 0) throw new IllegalArgumentException();
        double phiBase = vung.equals("noi_thanh") ? ((trongLuong > 5) ? 32000 : 15000) :
                vung.equals("ngoai_thanh") ? ((trongLuong > 3) ? 33000 : 20000) : 50000;
        if (trongLuong > 2) phiBase += 5000;
        if (laMember) phiBase *= 0.9;
        return phiBase;
    }

    // Bước 1: Khởi tạo nguồn dữ liệu (Data Provider)
    @DataProvider(name = "shippingData")
    public Object[][] dataContainer() {
        return new Object[][] {
                {3.0, "noi_thanh", false, 20000.0},   // P2
                {6.0, "noi_thanh", false, 37000.0},   // P3
                {2.0, "ngoai_thanh", false, 20000.0},  // P4
                {4.0, "ngoai_thanh", false, 38000.0},  // P5
                {1.0, "tinh_khac", false, 50000.0},    // P6
                {5.0, "tinh_khac", false, 55000.0},    // P7
                {5.0, "tinh_khac", true, 49500.0}      // P8
        };
    }

    // Bước 2: Liên kết hàm test với Data Provider
    @Test(dataProvider = "shippingData", description = "Test tinh phi ship bang DDT")
    public void testShippingWithDDT(double weight, String zone, boolean isMember, double expected) {
        double actual = tinhPhiShip(weight, zone, isMember);
        Assert.assertEquals(actual, expected, 0.001,
                "Lỗi tại: " + weight + "kg, " + zone + ", Member: " + isMember);
    }
}