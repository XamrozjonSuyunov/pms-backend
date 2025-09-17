package com.pms.dto;

import jakarta.validation.constraints.NotNull;

public record PlanRequest(
        @NotNull(message = "Year is required")
        Integer year,
        @NotNull(message = "Revenue is required")
        Long revenue
) {
}
