package com.pms.dto;

import com.pms.enums.ProjectStatus;
import com.pms.enums.ProjectType;

import java.time.LocalDate;

public record ProjectUpdateRequest(
        String name,
        ProjectType type,
        Long revenue,
        ProjectStatus status,
        LocalDate startDate,
        LocalDate endDate,
        Integer workingDays,
        Integer plannedRate,
        Integer actualRate,
        String pmName
) {
}
