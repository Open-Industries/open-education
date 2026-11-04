package org.openindustries.education.learner;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class LearnerResourceTest {

    @Test
    void createAndFetch() {
        String id = given().contentType("application/json")
                .body("{\"fullName\":\"Ada Lovelace\",\"learnerCode\":\"STU-2026-00123\",\"programCode\":\"CS-BSC\"}")
                .when().post("/api/v1/learners")
                .then().statusCode(201)
                .body("status", is("ENROLLED"))
                .body("learnerCodeMasked", endsWith("0123"))
                .extract().path("id");

        given().when().get("/api/v1/learners/" + id)
                .then().statusCode(200).body("status", is("ENROLLED"));
    }

    @Test
    void transitionWithoutBody() {
        // Lifecycle transitions take no body and must not require a Content-Type (no 415).
        String id = given().contentType("application/json")
                .body("{\"fullName\":\"Ada Lovelace\",\"learnerCode\":\"STU-2026-00123\",\"programCode\":\"CS-BSC\"}")
                .when().post("/api/v1/learners")
                .then().statusCode(201)
                .extract().path("id");

        given().when().post("/api/v1/learners/" + id + "/suspend")
                .then().statusCode(200).body("status", is("SUSPENDED"));
    }
}
