package com.example;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserApiTest extends ApiBaseTest {

    @Test
    public void testGetUsersPage1() {
        given(requestSpec)
                .queryParam("page", 1)
                .when()
                .get("/api/users")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .body("page", equalTo(1))
                .body("total_pages", greaterThan(0))
                .body("data.size()", greaterThanOrEqualTo(1));
    }

    @Test
    public void testGetUsersPage2() {
        given(requestSpec)
                .queryParam("page", 2)
                .when()
                .get("/api/users")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .body("page", equalTo(2))
                .body("data[0].id", notNullValue())
                .body("data[0].email", notNullValue())
                .body("data[0].first_name", notNullValue())
                .body("data[0].last_name", notNullValue())
                .body("data[0].avatar", notNullValue());
    }

    @Test
    public void testGetUserById() {
        given(requestSpec)
                .when()
                .get("/api/users/3")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .body("data.id", equalTo(3))
                .body("data.email", containsString("@reqres.in"))
                .body("data.first_name", not(emptyString()));
    }

    @Test
    public void testUserNotFound() {
        given(requestSpec)
                .when()
                .get("/api/users/9999")
                .then()
                .statusCode(404)
                .body("$", anEmptyMap());
    }

    @Test
    public void testSimple() {
        given()
                .header("User-Agent", "Mozilla/5.0")
                .when()
                .get("http://reqres.in/api/users?page=1")
                .then()
                .statusCode(200);
    }
}