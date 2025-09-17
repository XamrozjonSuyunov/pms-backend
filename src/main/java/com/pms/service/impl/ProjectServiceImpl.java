package com.pms.service.impl;

import com.pms.dto.*;
import com.pms.entity.Project;
import com.pms.enums.ProjectStatus;
import com.pms.exception.ErrorCode;
import com.pms.exception.ErrorMessageException;
import com.pms.mapper.ProjectMapper;
import com.pms.repository.ProjectRepository;
import com.pms.service.PlanService;
import com.pms.service.ProjectService;
import com.pms.specification.ProjectSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static com.pms.util.Utils.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository repository;
    private final ProjectMapper mapper;

    private final PlanService planService;

    @Override
    public Page<ProjectResponse> getProjects(ProjectFilter filter) {
        return repository.findAll(ProjectSpecification.filter(filter), filter.toPageable())
                .map(mapper::toProjectResponse);
    }

    @Override
    public ProjectStatsResponse getProjectStats() {
        return repository.getProjectStats();
    }

    @Override
    public ProjectResponse getProject(Long projectId) {
        return mapper.toProjectResponse(findProjectOrThrow(projectId));
    }

    @Override
    @Transactional
    public ProjectResponse createProject(ProjectCreateRequest request) {
        log.info("Creating project with name: {}", request.name());
        validateDatesAndWorkingDays(request.startDate(), request.endDate(), request.workingDays());

        var project = mapper.toProject(request);
        repository.save(project);
        log.info("Project {} created successfully", project.getId());
        return mapper.toProjectResponse(project);
    }

    @Override
    @Transactional
    public ProjectResponse updateProject(Long projectId, ProjectUpdateRequest request) {
        log.info("Updating project with id: {}", projectId);
        var project = findProjectOrThrow(projectId);

        if (project.getStatus() == ProjectStatus.DROPPED) {
            throw new ErrorMessageException("Project is dropped", ErrorCode.BadRequest);
        }

        if (request.startDate() != null && request.endDate() != null) {
            validateDatesAndWorkingDays(request.startDate(), request.endDate(), request.workingDays());
        }

        mapper.updateProjectFromRequest(request, project);
        repository.save(project);
        log.info("Project {} updated successfully", project.getId());
        return mapper.toProjectResponse(project);
    }

    @Override
    @Transactional
    public void deleteProject(Long projectId) {
        log.info("Deleting project with id: {}", projectId);
        var project = findProjectOrThrow(projectId);
        project.setStatus(ProjectStatus.DROPPED);

        repository.save(project);
        log.info("Project {} deleted successfully", project.getId());
    }

    @Override
    public ProjectMonthlySaleResponse getProjectMonthlySales(Long projectId) {
        return mapper.toProjectMonthlySaleResponse(findProjectOrThrow(projectId));
    }

    @Override
    public List<SalesRevenueResponse> getMonthlySalesRevenue(Integer year) {
        var projects = repository.getProjectsByStatusInAndYear(List.of(ProjectStatus.COMPLETED.name(), ProjectStatus.DELAYED.name(), ProjectStatus.ONGOING.name()), year);

        return Arrays.stream(Month.values()).map(month -> {
                BigDecimal totalRevenue = projects.stream()
                        .map(project -> getRevenueForMonth(project, year, month.getValue()))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                return new SalesRevenueResponse(month.name(), totalRevenue);
            }).toList();
    }

    @Override
    public List<TopRevenueRatioResponse> getTopRevenueRatiosForYear(Integer year) {
        return repository.findRevenueShareByYear(year).stream()
                .map(mapper::toTopRevenueRatioResponse).toList();
    }

    @Override
    public ProjectPlanResponse getProjectPlanForYear(Integer year) {
        var plan = planService.getPlan(year);
        var currentRevenue = repository.getRevenueByStatusAndYear(ProjectStatus.COMPLETED.name(), year);

        return mapper.toProjectPlanResponse(year, plan, currentRevenue);
    }

    @Override
    public List<ProjectDelayedPMResponse> getDelayedProjectPMsForYear(Integer year) {
        return repository.getProjectsByStatusAndYear(ProjectStatus.DELAYED.name(), year)
                .stream()
                .map(mapper::toDelayedPMResponse)
                .filter(response -> response.percent() > 0)
                .sorted(Comparator.comparingDouble(ProjectDelayedPMResponse::percent).reversed())
                .toList();
    }

    private Project findProjectOrThrow(Long projectId) {
        return repository.findById(projectId)
                .orElseThrow(() -> new ErrorMessageException("Project not found", ErrorCode.NotFound));
    }

    private void validateDatesAndWorkingDays(LocalDate startDate, LocalDate endDate, Integer workingDays) {
        if (startDate.isAfter(endDate)) {
            throw new ErrorMessageException("Start date must be before end date", ErrorCode.BadRequest);
        }

        int daysBetween = Math.toIntExact(ChronoUnit.DAYS.between(startDate, endDate) + 1);
        if (!Objects.equals(workingDays, daysBetween)) {
            throw new ErrorMessageException("Working days must be equal to total days", ErrorCode.BadRequest);
        }
    }
}
