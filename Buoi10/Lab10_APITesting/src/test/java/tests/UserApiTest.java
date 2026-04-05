package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class UserApiTest extends ApiBaseTest {
    @BeforeClass
    public void setup() { switchToJsonPlaceholder(); }

    @Test(description = "7.4: GET /users - Check 10 users")
    public void testGetUsers() {
        given(requestSpec).when().get("/users")
                .then().statusCode(200).body("size()", equalTo(10));
    }

    @Test(description = "7.4: Validate User Schema with Nested Address")
    public void testUserSchema() {
        given(requestSpec).when().get("/users/1")
                .then().statusCode(200)
                .body("address.city", notNullValue())
                .body("address.zipcode", notNullValue())
                .body(matchesJsonSchemaInClasspath("schemas/UserSchema.json"));
    }
}