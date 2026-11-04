package org.openindustries.education.course;

import java.util.UUID;

/** Course aggregate — Course catalogue with credit values and modalities. */
public class Course {
    public UUID id;
        public String name;
        public String modality;
        public int credits;
    public String status;

    public static Course create(String name, String modality, int credits) {
        Course e = new Course();
        e.id = UUID.randomUUID();
        e.name = name;
        e.modality = modality;
        e.credits = credits;
        e.status = "DRAFT";
        return e;
    }
}
