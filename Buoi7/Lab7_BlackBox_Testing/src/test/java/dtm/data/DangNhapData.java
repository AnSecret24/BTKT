package dtm.data;

import org.testng.annotations.DataProvider;

public class DangNhapData {
    @DataProvider(name = "du_lieu_dang_nhap")
    public Object[][] getData() {
        return new Object[][] {

                {"standard_user", "secret_sauce", "THÀNH CONG", "Đăng nhập hợp lệ"},
                {"locked_out_user", "secret_sauce", "BỊ KHOA", "Tài khoản bị khóa"},
                {"wrong_user", "secret_sauce", "SAI THONG TIN", "Sai username"},
                {"standard_user", "wrong_pass", "SAI THONG TIN", "Sai password"},
                {"", "secret_sauce", "TRUONG TRONG", "Để trống username"}
        };
    }
}
