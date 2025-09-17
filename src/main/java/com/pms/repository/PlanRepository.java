package com.pms.repository;

import com.pms.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanRepository extends JpaRepository<Plan, Integer> {
    Optional<Plan> findByYear(Integer year);
}
