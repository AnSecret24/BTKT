package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CommentApiTest extends ApiBaseTest {
    @BeforeClass
    public void setup() { switchToJsonPlaceholder(); }

    @Test(description = "7.3: GET /posts/1/comments - Check 5 comments")
    public void testComments() {
        given(requestSpec).when().get("/posts/1/comments")
                .then().spec(responseSpec).statusCode(200)
                .body("size()", equalTo(5))
                .body("postId", everyItem(equalTo(1)))
                .body("[0].email", notNullValue());
    }
}