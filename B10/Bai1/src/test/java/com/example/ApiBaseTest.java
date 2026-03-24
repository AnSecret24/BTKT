package com.example;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.http.ContentType;

import org.testng.annotations.BeforeClass;

import static org.hamcrest.Matchers.lessThan;

public class ApiBaseTest {

    protected RequestSpecification requestSpec;
    protected ResponseSpecification responseSpec;

    @BeforeClass
    public void setup() {

        RestAssured.useRelaxedHTTPSValidation();
        // Request config
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://reqres.in")
                .setContentType(ContentType.JSON)
                .addHeader("User-Agent", "Mozilla/5.0")
                .build();

        // Response config
        responseSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectResponseTime(lessThan(5000L)) // < 3s
                .build();
    }
}
