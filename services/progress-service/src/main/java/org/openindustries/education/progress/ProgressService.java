package org.openindustries.education.progress;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ProgressService {

    @Inject
    ProgressRepository repository;

    public CreditProgress open(OpenCreditProgressRequest request) {
        return repository.saveAccount(CreditProgress.open(request.learnerId(), request.creditsRemaining()));
    }

    public CreditProgress get(UUID id) {
        return repository.findAccount(id)
                .orElseThrow(() -> new DomainException(404, "CreditProgress not found: " + id));
    }

    /**
     * Draws from the account. Invariant: the balance/capacity never goes negative —
     * an over-drawing operation is rejected (422) and nothing is recorded.
     */
    public CreditAward consume(UUID accountId, CreateCreditAwardRequest request) {
        CreditProgress account = get(accountId);
        if (!"ACTIVE".equals(account.status)) {
            throw new DomainException(409, "CreditProgress is not ACTIVE");
        }
        if (request.credits() > account.creditsRemaining) {
            throw new DomainException(422, "Insufficient capacity: requested " + request.credits()
                    + " exceeds available " + account.creditsRemaining);
        }
        account.creditsRemaining -= request.credits();
        repository.saveAccount(account);
        return repository.saveRecord(CreditAward.of(accountId, request.moduleType(), request.credits(), account.creditsRemaining));
    }

    public List<CreditAward> recordsOf(UUID accountId) {
        get(accountId);
        return repository.recordsOf(accountId);
    }
}
