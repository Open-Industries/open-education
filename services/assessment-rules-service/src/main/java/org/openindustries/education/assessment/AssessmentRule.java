package org.openindustries.education.assessment;

import java.util.UUID;

/**
 * AssessmentRule aggregate — a decision rule of this core: for one level, it guarantees a
 * capacity (maxAttempts) and a threshold (maxPlagiarismPct), with a precedence priority.
 * Only ACTIVE rules decide; every decision names its rule and its reason (audit).
 */
public class AssessmentRule {
    public UUID id;
    public String name;
    public String level;
    public long maxAttempts;
    public int maxPlagiarismPct;
    public int priority;
    public String status;

    public static AssessmentRule create(String name, String level, long maxAttempts, int maxPlagiarismPct, int priority) {
        AssessmentRule r = new AssessmentRule();
        r.id = UUID.randomUUID();
        r.name = name;
        r.level = level;
        r.maxAttempts = maxAttempts;
        r.maxPlagiarismPct = maxPlagiarismPct;
        r.priority = priority;
        r.status = "DRAFT";
        return r;
    }
}
