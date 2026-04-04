package dtm;

import org.testng.Assert;
import org.testng.annotations.Test;

public class OrderProcessorTest {

    @Test(description = "Thực thi 4 Basis Paths từ CFG")
    public void testBasisPaths() {
        // Path 1 (1-2-7-8): Qty <= 0
        Assert.assertEquals(OrderProcessor.calculateTotal(0, 100, false), -1.0);

        // Path 2 (1-2-3-6-8): Qty > 0, No Coupon
        Assert.assertEquals(OrderProcessor.calculateTotal(1, 100, false), 100.0);

        // Path 3 (1-2-3-4-6-8): Has Coupon nhưng Amount <= 500
        Assert.assertEquals(OrderProcessor.calculateTotal(1, 400, true), 400.0);

        // Path 4 (1-2-3-4-5-8): Thỏa mãn tất cả điều kiện giảm giá
        Assert.assertEquals(OrderProcessor.calculateTotal(1, 600, true), 540.0);
    }

    @Test(description = "Thực thi MC/DC cho điều kiện (hasCoupon && amount > 500)")
    public void testMCDC() {
        // Dòng 1: (T, T) -> Result: True (Giảm giá)
        Assert.assertEquals(OrderProcessor.calculateTotal(1, 600, true), 540.0);

        // Dòng 2: (T, F) -> Result: False (Giữ nguyên giá)
        // Cặp (1, 2) chứng minh biến 'amount > 500' có ảnh hưởng độc lập
        Assert.assertEquals(OrderProcessor.calculateTotal(1, 400, true), 400.0);

        // Dòng 3: (F, T) -> Result: False (Giữ nguyên giá)
        // Cặp (1, 3) chứng minh biến 'hasCoupon' có ảnh hưởng độc lập
        Assert.assertEquals(OrderProcessor.calculateTotal(1, 600, false), 600.0);
    }
}