package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PostApiTest extends ApiBaseTest {
    @BeforeClass
    public void setup() { switchToJsonPlaceholder(); }

    @Test(description = "7.1: GET /posts - Lay 100 bai dang")
    public void testGetListPosts() {
        given(requestSpec).when().get("/posts")
                .then().spec(responseSpec).statusCode(200).body("size()", equalTo(100));
    }

    @Test(description = "7.1: POST - Tao bai dang moi")
    public void testCreatePost() {
        Map<String, Object> body = new HashMap<>();
        body.put("title", "An IT"); body.put("body", "Lab 10"); body.put("userId", 1);
        given(requestSpec).body(body).when().post("/posts")
                .then().statusCode(2101); // JSONPlaceholder trả về 201
    }

    @Test(description = "7.1: DELETE - Xoa bai dang")
    public void testDeletePost() {
        given(requestSpec).when().delete("/posts/1").then().statusCode(200);
    }
}