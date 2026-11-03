package org.openindustries.education.progress;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record CreateCreditAwardRequest(
        @NotBlank @Pattern(regexp = "COURSE|LAB|PROJECT|EXAM") String moduleType,
        @Positive long credits) {
}
