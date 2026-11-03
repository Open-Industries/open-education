package org.openindustries.education.progress;

import java.util.UUID;

/**
 * CreditProgress aggregate — the guarded resource account of this core: a balance/capacity that
 * consumption operations draw from. Core invariant (ADR-0004): it NEVER goes negative;
 * an operation that would overdraw it is rejected and records nothing.
 */
public class CreditProgress {
    public UUID id;
        public java.util.UUID learnerId;
    public long creditsRemaining;
    public String status;

    public static CreditProgress open(java.util.UUID learnerId, long creditsRemaining) {
        CreditProgress a = new CreditProgress();
        a.id = UUID.randomUUID();
        a.learnerId = learnerId;
        a.creditsRemaining = creditsRemaining;
        a.status = "ACTIVE";
        return a;
    }
}
