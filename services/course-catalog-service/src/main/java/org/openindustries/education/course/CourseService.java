package org.openindustries.education.course;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class CourseService {

    @Inject
    CourseRepository repository;

    public Course create(CreateCourseRequest request) {
        return repository.save(Course.create(request.name(), request.modality(), request.credits()));
    }

    public Course get(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new DomainException(404, "Course not found: " + id));
    }

    public List<Course> list() {
        return repository.findAll();
    }

    public Course publish(UUID id) {
        Course entity = get(id);
        if (!"DRAFT".equals(entity.status)) {
            throw new DomainException(409, "Only a DRAFT course can transition via publish");
        }
        entity.status = "PUBLISHED";
        return repository.save(entity);
    }
}
