package com.pms.dto;

import com.pms.enums.ProjectType;
import lombok.Builder;

import java.util.List;

@Builder
public record TopRevenueRatioResponse(
        ProjectType type,
        List<String> projectNames
) {
}
