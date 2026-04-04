package dtm;

public class OrderProcessor {
    /**
     * @param qty Số lượng sản phẩm
     * @param amount Tổng số tiền
     * @param hasCoupon Có mã giảm giá hay không
     * @return Số tiền thanh toán cuối cùng, -1 nếu dữ liệu lỗi
     */
    public static double calculateTotal(int qty, double amount, boolean hasCoupon) {
        // Nút 2: [D1] quantity > 0?
        if (qty <= 0) {
            return -1; // Nút 7: Báo lỗi số lượng
        }

        // Nút 3 & 4: [D2 && D3] hasCoupon == true AND amount > 500?
        if (hasCoupon && amount > 500) {
            return amount * 0.9; // Nút 5: Áp dụng giảm giá 10%
        }

        return amount; // Nút 6: Tính tiền bình thường
    }
}