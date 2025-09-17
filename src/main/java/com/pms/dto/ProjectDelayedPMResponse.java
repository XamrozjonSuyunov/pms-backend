package com.pms.dto;

import lombok.Builder;

@Builder
public record ProjectDelayedPMResponse(
        String pmName,
        Integer percent
) {
}
