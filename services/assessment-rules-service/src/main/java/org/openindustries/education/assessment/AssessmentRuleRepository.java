package org.openindustries.education.assessment;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/** Outbound port; default in-memory adapter, production adapter is JPA on this service's own database. */
@ApplicationScoped
public class AssessmentRuleRepository {
    private final ConcurrentHashMap<UUID, AssessmentRule> store = new ConcurrentHashMap<>();

    public AssessmentRule save(AssessmentRule rule) {
        store.put(rule.id, rule);
        return rule;
    }

    public Optional<AssessmentRule> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<AssessmentRule> findAll() {
        return List.copyOf(store.values());
    }

    /** Active rules for a scope, highest priority (lowest number) first. */
    public List<AssessmentRule> activeFor(String level) {
        return store.values().stream()
                .filter(r -> r.level.equals(level) && "ACTIVE".equals(r.status))
                .sorted(Comparator.comparingInt(r -> r.priority))
                .toList();
    }
}
