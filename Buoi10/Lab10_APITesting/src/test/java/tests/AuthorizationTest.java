package tests;

import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AuthorizationTest extends ApiBaseTest {

    // --- PHẦN A: KIỂM THỬ CƠ BẢN ---

    @Test(description = "Test 4.1: Đăng ký (Register) thành công")
    public void testRegisterSuccess() {
        Map<String, String> body = new HashMap<>();
        body.put("email", "eve.holt@reqres.in");
        body.put("password", "pistol");

        given(requestSpec)
                .body(body)
                .when()
                .post("/register")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("token", not(emptyString())); //[cite: 517]
    }

    // --- PHẦN B: DATA-DRIVEN CHO ERROR HANDLING ---

    @DataProvider(name = "loginScenarios")
    public Object[][] loginScenarios() {
        return new Object[][] {
                // {Email, Password, Expected Status, Expected Error Message}
                {"eve.holt@reqres.in", "cityslicka", 200, null}, //[cite: 528-530]
        {"eve.holt@reqres.in", "", 400, "Missing password"},// [cite: 531-534]
        {"", "cityslicka", 400, "Missing email or username"}, //[cite: 535-537]
        {"notexist@reqres.in", "wrongpass", 400, "user not found"} //[cite: 538-541]
        };
    }

    @Test(dataProvider = "loginScenarios", description = "Test 4.2: Các kịch bản Login lỗi và thành công")
    public void testLoginScenarios(String email, String password, int expectedStatus, String expectedError) {
        Map<String, String> body = new HashMap<>();
        body.put("email", email);
        if (!password.isEmpty()) {
            body.put("password", password); //[cite: 553-556]
        }

        ValidatableResponse response = given(requestSpec)
                .body(body)
                .when()
                .post("/login")
                .then()
                .statusCode(expectedStatus); //[cite: 560]


        if (expectedError != null) {
            response.body("error", containsString(expectedError)); //[cite: 564]
        } else {
            response.body("token", notNullValue()); //[cite: 510]
        }
    }
}