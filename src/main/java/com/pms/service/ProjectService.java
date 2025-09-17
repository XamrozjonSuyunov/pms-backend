package com.pms.service;

import com.pms.dto.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProjectService {
    Page<ProjectResponse> getProjects(ProjectFilter filter);

    ProjectStatsResponse getProjectStats();

    ProjectResponse getProject(Long projectId);

    ProjectResponse createProject(@Valid ProjectCreateRequest request);

    ProjectResponse updateProject(Long projectId, @Valid ProjectUpdateRequest request);

    void deleteProject(Long projectId);

    ProjectMonthlySaleResponse getProjectMonthlySales(Long projectId);

    List<SalesRevenueResponse> getMonthlySalesRevenue(Integer year);

    List<TopRevenueRatioResponse> getTopRevenueRatiosForYear(Integer year);

    ProjectPlanResponse getProjectPlanForYear(Integer year);

    List<ProjectDelayedPMResponse> getDelayedProjectPMsForYear(Integer year);

}
