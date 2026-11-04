package org.openindustries.education.learner;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record CreateLearnerRequest(
        @NotBlank String fullName,
        @NotBlank String learnerCode,
        @NotBlank String programCode) {
}
