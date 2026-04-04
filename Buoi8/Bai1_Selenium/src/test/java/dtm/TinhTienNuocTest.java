package dtm;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TinhTienNuocTest {
    public static double tinhTienNuoc(int soM3, String loaiKhachHang) {
        if (soM3 <= 0) return 0;
        double don_gia = 0;
        if (loaiKhachHang.equals("ho_ngheo")) {
            don_gia = 5000;
        } else if (loaiKhachHang.equals("dan_cu")) {
            if (soM3 <= 10) {
                don_gia = 7500;
            } else if (soM3 <= 20) {
                don_gia = 9900;
            } else {
                don_gia = 11400;
            }
        } else { // kinh_doanh
            don_gia = 22000;
        }
        return soM3 * don_gia;
    }

    @Test(description = "100% Branch Coverage cho ham tinhTienNuoc")
    public void testBranchCoverage() {
        Assert.assertEquals(tinhTienNuoc(0, "dan_cu"), 0.0);
        Assert.assertEquals(tinhTienNuoc(15, "ho_ngheo"), 75000.0);
        Assert.assertEquals(tinhTienNuoc(5, "dan_cu"), 37500.0);
        Assert.assertEquals(tinhTienNuoc(15, "dan_cu"), 148500.0);
        Assert.assertEquals(tinhTienNuoc(25, "dan_cu"), 285000.0);
        Assert.assertEquals(tinhTienNuoc(10, "kinh_doanh"), 220000.0);
    }
}