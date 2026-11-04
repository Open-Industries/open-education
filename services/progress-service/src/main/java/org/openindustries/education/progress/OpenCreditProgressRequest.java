package org.openindustries.education.progress;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record OpenCreditProgressRequest(
        @NotNull java.util.UUID learnerId,
        @PositiveOrZero long creditsRemaining) {
}
