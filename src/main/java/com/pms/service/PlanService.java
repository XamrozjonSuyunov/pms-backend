package com.pms.service;

import com.pms.dto.PlanRequest;
import com.pms.dto.PlanResponse;

public interface PlanService {
    PlanResponse getPlan(Integer year);

    PlanResponse upsertPlan(PlanRequest request);
}
