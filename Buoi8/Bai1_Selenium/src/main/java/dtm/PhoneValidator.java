package dtm;

public class PhoneValidator {
    public static boolean isValid(String phone) {
        if (phone == null) return false;

        // 1. Kiem tra ky tu (D1)
        if (!phone.matches("^[0-9+ ]+$")) return false;

        // 2. Chuan hoa (Action N3)
        String temp = phone.replace(" ", "");
        if (temp.startsWith("+84")) {
            temp = "0" + temp.substring(3);
        }

        // 3. Kiem tra do dai (D2)
        if (temp.length() != 10) return false;

        // 4. Kiem tra dau so (D3)
        return temp.matches("^(03|05|07|08|09)[0-9]{8}$");
    }
}