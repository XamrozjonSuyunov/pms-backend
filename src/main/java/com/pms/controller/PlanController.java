package com.pms.controller;

import com.pms.dto.PlanRequest;
import com.pms.dto.PlanResponse;
import com.pms.service.PlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Plans", description = "Plan management APIs")
@RestController
@RequestMapping("/api/v1/plans")
@RequiredArgsConstructor
public class PlanController {
    private final PlanService planService;

    @Operation(summary = "Get plan by year")
    @GetMapping
    public ResponseEntity<PlanResponse> getPlan(@RequestParam Integer year) {
        var response = planService.getPlan(year);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Upsert plan")
    @PostMapping("/upsert")
    public ResponseEntity<PlanResponse> upsertPlan(@Valid @RequestBody PlanRequest request) {
        var response = planService.upsertPlan(request);
        return ResponseEntity.ok(response);
    }
}
