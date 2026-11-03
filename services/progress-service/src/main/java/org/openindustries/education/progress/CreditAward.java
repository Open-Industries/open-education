package org.openindustries.education.progress;

import java.time.Instant;
import java.util.UUID;

/** One guarded consumption record — the auditable evidence of a draw on a CreditProgress. */
public class CreditAward {
    public UUID id;
    public UUID accountId;
    public String moduleType;
    public long credits;
    public long remainingAfter;
    public Instant recordedAt;

    public static CreditAward of(UUID accountId, String moduleType, long credits, long remainingAfter) {
        CreditAward r = new CreditAward();
        r.id = UUID.randomUUID();
        r.accountId = accountId;
        r.moduleType = moduleType;
        r.credits = credits;
        r.remainingAfter = remainingAfter;
        r.recordedAt = Instant.now();
        return r;
    }
}
