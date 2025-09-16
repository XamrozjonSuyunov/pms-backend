package com.pms.dto;

import com.pms.enums.ProjectStatus;
import com.pms.enums.ProjectType;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ProjectResponse(
        Long id,
        String name,
        ProjectType type,
        Long revenue,
        LocalDate startDate,
        LocalDate endDate,
        Integer workingDays,
        String pmName,
        Integer plannedRate,
        Integer actualRate,
        ProjectStatus status
) {
}
