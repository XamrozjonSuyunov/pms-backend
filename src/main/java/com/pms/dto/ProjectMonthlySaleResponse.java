package com.pms.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.Map;

@Builder
public record ProjectMonthlySaleResponse(
        Long id,
        String name,
        Long revenue,
        Map<String, BigDecimal> monthlySales
) {
}
