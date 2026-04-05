package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SlaPerformanceTest extends ApiBaseTest {

    @DataProvider(name = "apiSlaData")
    public Object[][] apiSlaData() {
        return new Object[][] {
                // {Endpoint, Expected Status, Max Response Time (ms)}
                { "/users", 200, 2000L }, //[cite: 569]
        { "/users/2", 200, 1500L }, //[cite: 569]
        { "/users/3", 200, 2000L },
        { "/login", 200, 2000L } //[cite: 569]
        };
    }

    @Test(dataProvider = "apiSlaData", description = "Bài 5: Kiểm thử hiệu năng theo SLA")
    public void testApiResponseTimeSLA(String endpoint, int expectedStatus, long maxMs) {
        given(requestSpec)
                .when()
                .get(endpoint)
                .then()
                .statusCode(expectedStatus)
                // Kiểm tra thời gian phản hồi có nhỏ hơn mức SLA cho phép không
                .time(lessThan(maxMs)); //[cite: 424, 468]
    }

    @Test(description = "Bài 5: Đo và log thời gian phản hồi thực tế")
    public void testMeasureActualResponseTime() {
        long startTime = System.currentTimeMillis(); //[cite: 427]

        given(requestSpec)
                .when()
                .get("/users/1")
                .then()
                .statusCode(200);

        long endTime = System.currentTimeMillis();
        long elapsed = endTime - startTime; //[cite: 432-433]

        System.out.println("[PERF] API /users/1 tốn: " + elapsed + "ms"); //[cite: 434-437]

        // Assert thủ công nếu vượt quá 3s
        org.testng.Assert.assertTrue(elapsed < 3000, "Thời gian phản hồi vượt quá SLA 3s!"); //[cite: 438-440]
    }
}