package org.openindustries.education.assessment;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record CreateAssessmentRuleRequest(
        @NotBlank String name,
        @NotBlank @Pattern(regexp = "UNDERGRADUATE|GRADUATE|PROFESSIONAL") String level,
        @Positive long maxAttempts,
        @Positive int maxPlagiarismPct,
        @Min(1) @Max(100) int priority) {
}
