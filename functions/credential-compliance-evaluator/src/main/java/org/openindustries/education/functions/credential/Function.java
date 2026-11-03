package org.openindustries.education.functions.credential;

import io.quarkus.funqy.Funq;

import java.util.ArrayList;
import java.util.List;

/**
 * Serverless function (Knative / OpenShift Serverless).
 * Compares missing requirements and completion percentage against credential targets; one finding per breach.
 * Compares measured values with targets and returns a verdict with one finding per
 * breach — machine-checkable assurance evidence.
 */
public class Function {

    public record Kpi(String learnerId, int measuredMissingRequirements, int targetMissingRequirements,
                      double measuredCompletionPct, double targetCompletionPct) {
    }

    public record Verdict(String learnerId, boolean compliant, List<String> findings) {
    }

    @Funq
    public Verdict credentialComplianceEvaluator(Kpi kpi) {
        List<String> findings = new ArrayList<>();
        if (kpi.measuredMissingRequirements() > kpi.targetMissingRequirements()) {
            findings.add("missingRequirements " + kpi.measuredMissingRequirements() + " exceeds target " + kpi.targetMissingRequirements());
        }
        if (kpi.measuredCompletionPct() < kpi.targetCompletionPct()) {
            findings.add("completionPct " + kpi.measuredCompletionPct() + " below target " + kpi.targetCompletionPct());
        }
        return new Verdict(kpi.learnerId(), findings.isEmpty(), findings);
    }
}
