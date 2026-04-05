package tests;

import org.testng.annotations.Test;
import tests.models.CreateUserRequest;
import tests.models.UserResponse;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserCrudTest extends ApiBaseTest {
    private String newUserId;

    @Test(priority = 1, description = "POST: Tạo user mới")
    public void testCreateUser() {
        CreateUserRequest userReq = new CreateUserRequest("An IT", "QA Engineer");

        UserResponse response = given(requestSpec)
                .body(userReq)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("An IT"))
                .body("id", notNullValue())
                .extract().as(UserResponse.class);

        newUserId = response.getId(); // Lưu ID lại để dùng cho bài test sau
        System.out.println("ID User vừa tạo: " + newUserId);
    }

    @Test(priority = 2, description = "PUT: Cập nhật toàn bộ thông tin user")
    public void testUpdateUser() {
        String updatedBody = "{\"name\":\"An IT Updated\", \"job\":\"Senior QA\"}";

        given(requestSpec)
                .body(updatedBody)
                .when()
                .put("/users/2") // Theo đề bài yêu cầu dùng ID 2
                .then()
                .statusCode(200)
                .body("job", equalTo("Senior QA"))
                .body("updatedAt", notNullValue());
    }

    @Test(priority = 3, description = "PATCH: Cập nhật một phần thông tin")
    public void testPatchUser() {
        given(requestSpec)
                .body("{\"job\":\"Automation Lead\"}")
                .when()
                .patch("/api/users/2")
                .then()
                .statusCode(200)
                .body("job", equalTo("Automation Lead"));
    }

    @Test(priority = 4, description = "DELETE: Xóa user")
    public void testDeleteUser() {
        given(requestSpec)
                .when()
                .delete("/users/2")
                .then()
                .statusCode(204);
    }
}