package com.pms.service.impl;

import com.pms.dto.PlanRequest;
import com.pms.dto.PlanResponse;
import com.pms.entity.Plan;
import com.pms.mapper.PlanMapper;
import com.pms.repository.PlanRepository;
import com.pms.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {
    private final PlanRepository repository;
    private final PlanMapper mapper;

    @Override
    public PlanResponse getPlan(Integer year) {
        return repository.findByYear(year).map(mapper::toPlanResponse).orElse(null);
    }

    @Override
    public PlanResponse upsertPlan(PlanRequest request) {
        Plan plan = repository.findByYear(request.year())
                .map(existingPlan -> {
                    mapper.updatePlanFromRequest(request, existingPlan);
                    return existingPlan;
                })
                .orElseGet(() -> mapper.toPlan(request));

        repository.save(plan);
        return mapper.toPlanResponse(plan);
    }
}
