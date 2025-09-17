package com.pms.dto;

import lombok.Builder;

@Builder
public record ProjectPlanResponse(
        Integer year,
        Long totalRevenue,
        Long currentRevenue
) {
}
