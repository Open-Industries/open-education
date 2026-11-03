package org.openindustries.education.progress;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/** Outbound port; default in-memory adapter, production adapter is JPA on this service's own database. */
@ApplicationScoped
public class ProgressRepository {
    private final ConcurrentHashMap<UUID, CreditProgress> accounts = new ConcurrentHashMap<>();
    private final CopyOnWriteArrayList<CreditAward> records = new CopyOnWriteArrayList<>();

    public CreditProgress saveAccount(CreditProgress account) {
        accounts.put(account.id, account);
        return account;
    }

    public Optional<CreditProgress> findAccount(UUID id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public CreditAward saveRecord(CreditAward record) {
        records.add(record);
        return record;
    }

    public List<CreditAward> recordsOf(UUID accountId) {
        return records.stream().filter(r -> r.accountId.equals(accountId)).toList();
    }
}
