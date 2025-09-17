package com.pms.dto;

import com.pms.enums.ProjectType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ProjectCreateRequest(
        @NotBlank(message = "Name is required")
        String name,
        @NotNull(message = "Project type is required")
        ProjectType type,
        @NotNull(message = "Revenue is required")
        Long revenue,
        @NotNull(message = "Start date is required")
        LocalDate startDate,
        @NotNull(message = "End date is required")
        LocalDate endDate,
        @NotNull(message = "Working days is required")
        Integer workingDays,
        @NotNull(message = "pmName is required")
        String pmName
) {
}
