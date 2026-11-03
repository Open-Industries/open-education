package org.openindustries.education.functions.engagement;

import io.quarkus.funqy.Funq;

/**
 * Serverless function (Knative / OpenShift Serverless).
 * Scores early-warning signals (absence, inactivity, late submissions) with an auditable rationale.
 * Transparent, auditable rules: NORMAL (< 35), WARNING (35-69), CRITICAL (>= 70);
 * the rationale is returned with the score for audit (ISO/IEC 27002 A.8.15).
 * Stateless by design (12-Factor VI): all state arrives in the request.
 */
public class Function {

    public record Event(String learnerId, int absenceIndex, int inactivityIndex, int lateIndex) {
    }

    public record Score(String learnerId, int score, String decision, String rationale) {
    }

    @Funq
    public Score engagementRiskScorer(Event event) {
        int score = 0;
        StringBuilder why = new StringBuilder();
        if (event.absenceIndex() >= 70) {
            score += 40;
            why.append("absenceIndex >= 70; ");
        }
        if (event.inactivityIndex() >= 60) {
            score += 30;
            why.append("inactivityIndex >= 60; ");
        }
        if (event.lateIndex() >= 50) {
            score += 30;
            why.append("lateIndex >= 50; ");
        }
        String decision = score >= 70 ? "CRITICAL" : score >= 35 ? "WARNING" : "NORMAL";
        return new Score(event.learnerId(), score, decision,
                why.length() == 0 ? "no signals" : why.toString().trim());
    }
}
