package com.pms.mapper;

import com.pms.dto.ProjectCreateRequest;
import com.pms.dto.ProjectResponse;
import com.pms.dto.ProjectUpdateRequest;
import com.pms.entity.Project;
import org.springframework.context.annotation.Configuration;

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
                .plannedRate(project.getPlannedRate())
                .actualRate(project.getActualRate())
                .status(project.getStatus())
                .build();
    }

    public Project toProject(ProjectCreateRequest request) {
        return Project.builder()
                .name(request.name())
                .type(request.type())
                .revenue(request.revenue())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .workingDays(request.workingDays())
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
        project.setPlannedRate(request.plannedRate() != null ? request.plannedRate() : project.getPlannedRate());
        project.setActualRate(request.actualRate() != null ? request.actualRate() : project.getActualRate());
        project.setPmName(request.pmName() != null ? request.pmName() : project.getPmName());
    }
}
