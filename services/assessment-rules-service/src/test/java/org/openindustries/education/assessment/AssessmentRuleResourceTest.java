package org.openindustries.education.assessment;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class AssessmentRuleResourceTest {

    @Test
    void activateThenEvaluateAllowed() {
        String id = given().contentType("application/json")
                .body("{\"name\":\"ug-standard\",\"level\":\"UNDERGRADUATE\",\"maxAttempts\":3,\"maxPlagiarismPct\":20,\"priority\":1}")
                .when().post("/api/v1/assessment-rules")
                .then().statusCode(201).body("status", is("DRAFT"))
                .extract().path("id");

        given().when().post("/api/v1/assessment-rules/" + id + "/activate")
                .then().statusCode(200).body("status", is("ACTIVE"));

        given().contentType("application/json")
                .body("{\"level\":\"UNDERGRADUATE\",\"requiredMaxPlagiarismPct\":25,\"requiredMaxAttempts\":2}")
                .when().post("/api/v1/assessment-rules/evaluate")
                .then().statusCode(200).body("decision", is("ALLOWED"));
    }

    @Test
    void evaluateWithoutRuleIsDenied() {
        given().contentType("application/json")
                .body("{\"level\":\"GRADUATE\",\"requiredMaxPlagiarismPct\":25,\"requiredMaxAttempts\":2}")
                .when().post("/api/v1/assessment-rules/evaluate")
                .then().statusCode(200).body("decision", is("DENIED"));
    }
}
