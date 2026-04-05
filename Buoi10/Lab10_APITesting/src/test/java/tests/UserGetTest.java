package tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserGetTest extends ApiBaseTest {

    @Test(description = "Test 1: GET /api/users?page=1")
    public void testGetUsersPage1() {
        given(requestSpec) // Sử dụng requestSpec từ lớp cha [cite: 304, 486]
                .queryParam("page", 1)
                .when()
                .get("/users")
                .then()
                .spec(responseSpec) // Sử dụng responseSpec để check SLA [cite: 309, 486]
                .statusCode(200)
                .body("page", equalTo(1))
                .body("total_pages", greaterThan(0))
                .body("data.size()", greaterThan(0)); // Kiểm tra data không rỗng
    }

    @Test(description = "Test 2: GET /api/users?page=2")
    public void testGetUsersPage2() {
        given(requestSpec)
                .queryParam("page", 2)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("page", equalTo(2))
                // Kiểm tra mỗi user có đủ id, email, first_name, last_name, avatar [cite: 482, 483]
                .body("data.id", everyItem(notNullValue()))
                .body("data.email", everyItem(notNullValue()))
                .body("data.first_name", everyItem(notNullValue()))
                .body("data.last_name", everyItem(notNullValue()))
                .body("data.avatar", everyItem(notNullValue()));
    }

    @Test(description = "Test 3: GET /api/users/3")
    public void testGetSingleUser() {
        given(requestSpec)
                .when()
                .get("/users/3")
                .then()
                .statusCode(200)
                .body("data.id", equalTo(3))
                .body("data.email", containsString("@reqres.in")) // Đúng định dạng [cite: 484]
                .body("data.first_name", not(emptyString()));
    }

    @Test(description = "Test 4: GET /api/users/9999 (Không tồn tại)")
    public void testGetNonExistUser() {
        given(requestSpec)
                .when()
                .get("/users/9999")
                .then()
                .statusCode(404) // Mong đợi lỗi 404 [cite: 485]
                .body("$", anEmptyMap()); // Body là object rỗng {} [cite: 133, 485]
    }
}