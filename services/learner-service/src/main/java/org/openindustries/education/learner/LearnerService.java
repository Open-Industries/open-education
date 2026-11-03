package org.openindustries.education.learner;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class LearnerService {

    @Inject
    LearnerRepository repository;

    public Learner create(CreateLearnerRequest request) {
        return repository.save(Learner.create(request.fullName(), request.learnerCode(), request.programCode()));
    }

    public Learner get(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new DomainException(404, "Learner not found: " + id));
    }

    public List<Learner> list() {
        return repository.findAll();
    }

    public Learner suspend(UUID id) {
        Learner entity = get(id);
        if ("GRADUATED".equals(entity.status)) {
            throw new DomainException(409, "A graduated learner is terminal");
        }
        if ("SUSPENDED".equals(entity.status)) {
            throw new DomainException(409, "Learner already SUSPENDED");
        }
        entity.status = "SUSPENDED";
        return repository.save(entity);
    }

    public Learner graduate(UUID id) {
        Learner entity = get(id);
        if ("GRADUATED".equals(entity.status)) {
            throw new DomainException(409, "A graduated learner is terminal");
        }
        if ("GRADUATED".equals(entity.status)) {
            throw new DomainException(409, "Learner already GRADUATED");
        }
        entity.status = "GRADUATED";
        return repository.save(entity);
    }
}
