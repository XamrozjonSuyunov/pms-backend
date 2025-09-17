package com.pms.mapper;

import com.pms.dto.*;
import com.pms.entity.Project;
import com.pms.enums.ProjectStatus;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

import static com.pms.util.Utils.calculateProgress;
import static com.pms.util.Utils.generateMonthlySales;

@Configuration
public class ProjectMapper {

    public ProjectResponse toProjectResponse(Project project) {
        return ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .type(project.getType())
                .revenue(project.getRevenue())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .workingDays(project.getWorkingDays())
                .pmName(project.getPmName())
                .plannedRate(calculateProgress(project.getStartDate(), project.getEndDate()))
                .actualRate(project.getActualRate())
                .status(project.getStatus())
                .build();
    }

    public Project toProject(ProjectCreateRequest request) {
        return Project.builder()
                .name(request.name())
                .type(request.type())
                .revenue(request.revenue())
                .status(ProjectStatus.PLANNED)
                .startDate(request.startDate())
                .endDate(request.endDate())
                .workingDays(request.workingDays())
                .plannedRate(calculateProgress(request.startDate(), request.endDate()))
                .actualRate(0)
                .pmName(request.pmName())
                .build();
    }

    public void updateProjectFromRequest(ProjectUpdateRequest request, Project project) {
        project.setName(request.name() != null ? request.name() : project.getName());
        project.setType(request.type() != null ? request.type() : project.getType());
        project.setRevenue(request.revenue() != null ? request.revenue() : project.getRevenue());
        project.setStatus(request.status() != null ? request.status() : project.getStatus());
        project.setStartDate(request.startDate() != null ? request.startDate() : project.getStartDate());
        project.setEndDate(request.endDate() != null ? request.endDate() : project.getEndDate());
        project.setWorkingDays(request.workingDays() != null ? request.workingDays() : project.getWorkingDays());
        project.setPlannedRate(calculateProgress(project.getStartDate(), project.getEndDate()));
        project.setActualRate(request.actualRate() != null ? request.actualRate() : project.getActualRate());
        project.setPmName(request.pmName() != null ? request.pmName() : project.getPmName());
    }

    public ProjectMonthlySaleResponse toProjectMonthlySaleResponse(Project project) {
        return ProjectMonthlySaleResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .revenue(project.getRevenue())
                .monthlySales(generateMonthlySales(project))
                .build();
    }

    public ProjectPlanResponse toProjectPlanResponse(Integer year, PlanResponse plan, Long currentRevenue) {
        return ProjectPlanResponse.builder()
                .year(year)
                .totalRevenue(plan != null ? plan.revenue() : 0)
                .currentRevenue(currentRevenue != null ? currentRevenue : 0)
                .build();
    }

    public ProjectDelayedPMResponse toDelayedPMResponse(Project project) {
        var planRate = calculateProgress(project.getStartDate(), project.getEndDate());
        var percentDifference = planRate - project.getActualRate();

        return new ProjectDelayedPMResponse(project.getPmName(), percentDifference);
    }

    public TopRevenueRatioResponse toTopRevenueRatioResponse(RevenueShareProjection revenueShareProjection) {
        return TopRevenueRatioResponse.builder()
                .type(revenueShareProjection.getType())
                .present(revenueShareProjection.getPresent())
                .projectNames(Arrays.stream(revenueShareProjection.getProjectNames().split(",")).limit(4).toList())
                .build();
    }
}
