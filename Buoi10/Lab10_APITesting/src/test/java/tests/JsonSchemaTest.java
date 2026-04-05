package tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JsonSchemaTest extends ApiBaseTest {

    @Test(description = "Test 3.1: Validate Schema danh sách User")
    public void testUserListSchema() {
        given(requestSpec)
                .queryParam("page", 1)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/user-list-schema.json"));
    }

    @Test(description = "Test 3.2: Validate Schema Single User (Nested)")
    public void testSingleUserSchema() {
        given(requestSpec)
                .when()
                .get("/users/2")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"));
    }

    @Test(description = "Test 3.3: Validate Schema tạo User mới")
    public void testCreateUserSchema() {
        String body = "{\"name\": \"An\", \"job\": \"Software Student\"}";
        given(requestSpec)
                .body(body)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body(matchesJsonSchemaInClasspath("schemas/create-user-schema.json"));
    }
}