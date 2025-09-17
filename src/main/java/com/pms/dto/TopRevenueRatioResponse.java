package com.pms.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record TopRevenueRatioResponse(
        String type,
        Double present,
        List<String> projectNames
) {
}
