package org.openindustries.education.functions.engagement;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class FunctionTest {

    @Test
    void healthyIsNormal() {
        given().contentType("application/json")
                .body("{\"learnerId\":\"l-1\",\"absenceIndex\":5,\"inactivityIndex\":8,\"lateIndex\":3}")
                .when().post("/engagementRiskScorer")
                .then().statusCode(200).body("decision", is("NORMAL")).body("score", is(0));
    }

    @Test
    void stackedSignalsAreCritical() {
        given().contentType("application/json")
                .body("{\"learnerId\":\"l-2\",\"absenceIndex\":85,\"inactivityIndex\":75,\"lateIndex\":66}")
                .when().post("/engagementRiskScorer")
                .then().statusCode(200).body("decision", is("CRITICAL")).body("score", is(100));
    }
}
