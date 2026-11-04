package org.openindustries.education.course;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/** Outbound port; default in-memory adapter, production adapter is JPA on this service's own database. */
@ApplicationScoped
public class CourseRepository {
    private final ConcurrentHashMap<UUID, Course> store = new ConcurrentHashMap<>();

    public Course save(Course entity) {
        store.put(entity.id, entity);
        return entity;
    }

    public Optional<Course> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Course> findAll() {
        return List.copyOf(store.values());
    }
}
