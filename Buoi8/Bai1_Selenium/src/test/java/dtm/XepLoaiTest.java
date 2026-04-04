package dtm;

import org.testng.Assert;
import org.testng.annotations.Test;

public class XepLoaiTest {

    public static String xepLoai(int diemTB, boolean coThiLai) {
        if (diemTB < 0 || diemTB > 10) return "Diem khong hop le";
        if (diemTB >= 8.5) return "Gioi";
        if (diemTB >= 7.0) return "Kha";
        if (diemTB >= 5.5) return "Trung Binh";
        if (coThiLai) return "Thi lai";
        return "Yeu - Hoc lai";
    }

    @Test(description = "100% Branch Coverage")
    public void testXepLoaiBranchCoverage() {
        Assert.assertEquals(xepLoai(-1, false), "Diem khong hop le");
        Assert.assertEquals(xepLoai(9, false), "Gioi");
        Assert.assertEquals(xepLoai(7, false), "Kha");
        Assert.assertEquals(xepLoai(6, false), "Trung Binh");
        Assert.assertEquals(xepLoai(4, true), "Thi lai");
        Assert.assertEquals(xepLoai(4, false), "Yeu - Hoc lai");
    }
}