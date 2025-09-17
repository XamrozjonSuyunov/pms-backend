package com.pms.mapper;

import com.pms.dto.PlanRequest;
import com.pms.dto.PlanResponse;
import com.pms.entity.Plan;
import org.springframework.stereotype.Component;

@Component
public class PlanMapper {

    public PlanResponse toPlanResponse(Plan plan) {
        return PlanResponse.builder()
                .id(plan.getId())
                .year(plan.getYear())
                .revenue(plan.getRevenue())
                .build();
    }

    public void updatePlanFromRequest(PlanRequest request, Plan plan) {
        plan.setYear(request.year() != null ? request.year() : plan.getYear());
        plan.setRevenue(request.revenue() != null ? request.revenue() : plan.getRevenue());
    }

    public Plan toPlan(PlanRequest request) {
        return Plan.builder()
                .year(request.year())
                .revenue(request.revenue())
                .build();
    }
}
