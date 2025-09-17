package com.pms.dto;

import lombok.Builder;

@Builder
public record PlanResponse(
        Integer id,
        Integer year,
        Long revenue
) {
}
