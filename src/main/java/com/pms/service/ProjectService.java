package com.pms.service;

import com.pms.dto.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

public interface ProjectService {
    Page<ProjectResponse> getProjects(ProjectFilter filter);

    ProjectStatsResponse getProjectStats();

    ProjectResponse getProject(Long projectId);

    ProjectResponse createProject(@Valid ProjectCreateRequest request);

    ProjectResponse updateProject(Long projectId, @Valid ProjectUpdateRequest request);

    void deleteProject(Long projectId);
}
