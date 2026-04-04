package dtm;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PhoneValidatorTest {

    @Test(description = "Path 1: So hop le voi +84")
    public void testValidPhoneWithPrefix() {
        Assert.assertTrue(PhoneValidator.isValid("+84 912345678"));
    }

    @Test(description = "Path 2: Chua ky tu la")
    public void testInvalidChars() {
        Assert.assertFalse(PhoneValidator.isValid("0912abc567"));
    }

    @Test(description = "Path 3: Sai do dai (bien)")
    public void testInvalidLength() {
        Assert.assertFalse(PhoneValidator.isValid("035123456")); // 9 so
    }

    @Test(description = "Path 4: Sai dau so nha mang")
    public void testInvalidProvider() {
        Assert.assertFalse(PhoneValidator.isValid("0212345678"));
    }
}