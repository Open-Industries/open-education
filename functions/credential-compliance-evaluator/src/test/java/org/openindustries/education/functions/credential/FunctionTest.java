package org.openindustries.education.functions.credential;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class FunctionTest {

    @Test
    void compliant() {
        given().contentType("application/json")
                .body("{\"learnerId\":\"l-1\",\"measuredMissingRequirements\":0,\"targetMissingRequirements\":0,\"measuredCompletionPct\":100.0,\"targetCompletionPct\":100.0}")
                .when().post("/credentialComplianceEvaluator")
                .then().statusCode(200).body("compliant", is(true)).body("findings.size()", is(0));
    }

    @Test
    void breachIsReported() {
        given().contentType("application/json")
                .body("{\"learnerId\":\"l-2\",\"measuredMissingRequirements\":2,\"targetMissingRequirements\":0,\"measuredCompletionPct\":100.0,\"targetCompletionPct\":100.0}")
                .when().post("/credentialComplianceEvaluator")
                .then().statusCode(200).body("compliant", is(false)).body("findings.size()", is(1));
    }
}
