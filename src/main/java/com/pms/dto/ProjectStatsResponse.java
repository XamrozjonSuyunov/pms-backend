package com.pms.dto;

public record ProjectStatsResponse(
        long total,
        long delayed,
        long ongoing,
        long dropped,
        long completed
) {
}
