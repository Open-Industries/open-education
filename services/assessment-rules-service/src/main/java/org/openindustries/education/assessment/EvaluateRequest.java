package org.openindustries.education.assessment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record EvaluateRequest(
        @NotBlank @Pattern(regexp = "UNDERGRADUATE|GRADUATE|PROFESSIONAL") String level,
        @Positive int requiredMaxPlagiarismPct,
        @Positive long requiredMaxAttempts) {
}
