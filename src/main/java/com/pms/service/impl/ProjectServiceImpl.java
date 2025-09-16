package com.pms.service.impl;

import com.pms.dto.*;
import com.pms.enums.ProjectStatus;
import com.pms.exception.ErrorCode;
import com.pms.exception.ErrorMessageException;
import com.pms.mapper.ProjectMapper;
import com.pms.repository.ProjectRepository;
import com.pms.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository repository;
    private final ProjectMapper mapper;

    @Override
    public Page<ProjectResponse> getProjects(ProjectFilter filter) {
        return repository.getProjectsOrderByDatetimeCreated(filter.toPageable())
                .map(mapper::toProjectResponse);
    }

    @Override
    public ProjectStatsResponse getProjectStats() {
        return repository.getProjectStats();
    }

    @Override
    public ProjectResponse getProject(Long projectId) {
        var project = repository.findById(projectId).orElseThrow(() -> new ErrorMessageException("Project not found", ErrorCode.NotFound));

        return mapper.toProjectResponse(project);
    }

    @Override
    @Transactional
    public ProjectResponse createProject(ProjectCreateRequest request) {
        var project = mapper.toProject(request);

        var saved = repository.save(project);
        return mapper.toProjectResponse(saved);
    }

    @Override
    @Transactional
    public ProjectResponse updateProject(Long projectId, ProjectUpdateRequest request) {
        var project = repository.findById(projectId).orElseThrow(() -> new ErrorMessageException("Project not found", ErrorCode.NotFound));

        mapper.updateProjectFromRequest(request, project);

        var saved = repository.save(project);
        return mapper.toProjectResponse(saved);
    }

    @Override
    @Transactional
    public void deleteProject(Long projectId) {
        var project = repository.findById(projectId).orElseThrow(() -> new ErrorMessageException("Project not found", ErrorCode.NotFound));
        project.setStatus(ProjectStatus.DROPPED);

        repository.save(project);
    }
}
