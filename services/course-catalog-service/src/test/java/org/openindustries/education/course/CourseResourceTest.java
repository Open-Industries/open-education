package org.openindustries.education.course;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class CourseResourceTest {

    @Test
    void createAndTransition() {
        String id = given().contentType("application/json")
                .body("{\"name\":\"Distributed Systems\",\"modality\":\"HYBRID\",\"credits\":6}")
                .when().post("/api/v1/courses")
                .then().statusCode(201)
                .body("status", is("DRAFT"))
                .body("modality", is("HYBRID"))
                .extract().path("id");

        given().when().post("/api/v1/courses/" + id + "/publish")
                .then().statusCode(200).body("status", is("PUBLISHED"));
    }
}
