package org.openindustries.education.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record CreateCourseRequest(
        @NotBlank String name,
        @NotBlank @Pattern(regexp = "ONLINE|ON_CAMPUS|HYBRID") String modality,
        @Positive int credits) {
}
