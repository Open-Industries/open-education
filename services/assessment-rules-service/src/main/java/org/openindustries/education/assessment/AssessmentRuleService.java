package org.openindustries.education.assessment;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class AssessmentRuleService {

    @Inject
    AssessmentRuleRepository repository;

    public AssessmentRule create(CreateAssessmentRuleRequest request) {
        return repository.save(AssessmentRule.create(request.name(), request.level(),
                request.maxAttempts(), request.maxPlagiarismPct(), request.priority()));
    }

    public AssessmentRule get(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new DomainException(404, "AssessmentRule not found: " + id));
    }

    public List<AssessmentRule> list() {
        return repository.findAll();
    }

    public AssessmentRule activate(UUID id) {
        AssessmentRule rule = get(id);
        rule.status = "ACTIVE";
        return repository.save(rule);
    }

    public Evaluation evaluate(EvaluateRequest request) {
        var candidates = repository.activeFor(request.level());
        if (candidates.isEmpty()) {
            return new Evaluation("DENIED", null,
                    "No active rule for " + request.level());
        }
        for (AssessmentRule rule : candidates) {
            if (rule.maxPlagiarismPct <= request.requiredMaxPlagiarismPct()
                    && rule.maxAttempts >= request.requiredMaxAttempts()) {
                return new Evaluation("ALLOWED", rule.id,
                        "Rule '" + rule.name + "' covers the request");
            }
        }
        return new Evaluation("DEGRADED", candidates.get(0).id,
                "Active rules exist for " + request.level() + " but none meets the requirement");
    }
}
