package dtm;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TinhPhiShipTest {

    public double tinhPhiShip(double trongLuong, String vung, boolean laMember) {
        if (trongLuong <= 0) {
            throw new IllegalArgumentException("Trong luong phai lon hon 0");
        }

        double phiBase = 0;
        if (vung.equals("noi_thanh")) {
            phiBase = (trongLuong > 5) ? 32000 : 15000;
        } else if (vung.equals("ngoai_thanh")) {
            phiBase = (trongLuong > 3) ? 33000 : 20000;
        } else {
            phiBase = 50000;
        }

        // Kiem tra phu phi trong luong > 2kg
        if (trongLuong > 2) {
            phiBase += 5000;
        }

        // Kiem tra giam gia Member
        if (laMember) {
            phiBase *= 0.9;
        }

        return phiBase;
    }

    @Test(description = "Path Coverage - 8 Basis Paths")
    public void testBasisPaths() {
        double delta = 0.001; // Sai so cho phep khi so sanh double

        // P2: Noi thanh, nhe, no member
        Assert.assertEquals(tinhPhiShip(3, "noi_thanh", false), 20000.0, delta);

        // P3: Noi thanh, nang, no member
        Assert.assertEquals(tinhPhiShip(6, "noi_thanh", false), 37000.0, delta);

        // P4: Ngoai thanh, nhe, no member
        Assert.assertEquals(tinhPhiShip(2, "ngoai_thanh", false), 20000.0, delta);

        // P5: Ngoai thanh, nang, no member
        Assert.assertEquals(tinhPhiShip(4, "ngoai_thanh", false), 38000.0, delta);

        // P6: Tinh khac, nhe, no member
        Assert.assertEquals(tinhPhiShip(1, "tinh_khac", false), 50000.0, delta);

        // P7: Tinh khac, nang, no member
        Assert.assertEquals(tinhPhiShip(5, "tinh_khac", false), 55000.0, delta);

        // P8: Tinh khac, nang, la Member
        Assert.assertEquals(tinhPhiShip(5, "tinh_khac", true), 49500.0, delta);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, description = "P1: Trong luong khong hop le")
    public void testInvalidWeight() {
        tinhPhiShip(-1, "noi_thanh", false);
    }
}