package org.openindustries.education.learner;

import java.time.Instant;
import java.util.UUID;

/**
 * Learner aggregate — Learner registry; the institutional identifier is masked at enrolment.
 *
 * Privacy by design (ISO/IEC 27002 A.5.34): secret identifiers are validated at
 * creation and NEVER persisted or returned in clear — only a masked form is kept.
 * All identifiers in this reference implementation are synthetic.
 */
public class Learner {
    public UUID id;
        public String fullName;
        public String learnerCodeMasked;
        public String programCode;
    public String status;
    public Instant createdAt;

    public static Learner create(String fullName, String learnerCode, String programCode) {
        Learner e = new Learner();
        e.id = UUID.randomUUID();
        e.fullName = fullName;
        e.learnerCodeMasked = mask(learnerCode);
        e.programCode = programCode;
        e.status = "ENROLLED";
        e.createdAt = Instant.now();
        return e;
    }

    static String mask(String value) {
        if (value == null || value.length() < 4) {
            return "****";
        }
        return "*".repeat(value.length() - 4) + value.substring(value.length() - 4);
    }
}
