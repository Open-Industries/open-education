package org.openindustries.education.progress;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class ProgressResourceTest {

    @Test
    void consumptionDrawsDown() {
        String id = given().contentType("application/json")
                .body("{\"learnerId\":\"11111111-1111-1111-1111-111111111111\",\"creditsRemaining\":120}")
                .when().post("/api/v1/progress")
                .then().statusCode(201).body("creditsRemaining", is(120))
                .extract().path("id");

        given().contentType("application/json")
                .body("{\"moduleType\":\"COURSE\",\"credits\":30}")
                .when().post("/api/v1/progress/" + id + "/awards")
                .then().statusCode(200)
                .body("remainingAfter", is(90));
    }

    @Test
    void overdrawIsRejected() {
        String id = given().contentType("application/json")
                .body("{\"learnerId\":\"11111111-1111-1111-1111-111111111111\",\"creditsRemaining\":120}")
                .when().post("/api/v1/progress")
                .then().statusCode(201)
                .extract().path("id");

        given().contentType("application/json")
                .body("{\"moduleType\":\"COURSE\",\"credits\":300}")
                .when().post("/api/v1/progress/" + id + "/awards")
                .then().statusCode(422).body("status", is(422));

        given().when().get("/api/v1/progress/" + id)
                .then().statusCode(200).body("creditsRemaining", is(120));
    }
}
