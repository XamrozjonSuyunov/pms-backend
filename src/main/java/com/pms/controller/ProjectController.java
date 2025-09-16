package com.pms.controller;

import com.pms.dto.*;
import com.pms.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Projects", description = "Project management APIs")
@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @Operation(summary = "Get paginated list of projects")
    @GetMapping
    public Page<ProjectResponse> getProjects(@ParameterObject ProjectFilter filter) {
        return projectService.getProjects(filter);
    }

    @Operation(summary = "Get overall project statistics")
    @GetMapping("/stats")
    public ResponseEntity<ProjectStatsResponse> getProjectStats() {
        var projectStat = projectService.getProjectStats();
        return ResponseEntity.ok(projectStat);
    }

    @Operation(summary = "Get project by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProject(
            @PathVariable("id") Long projectId
    ) {
        var response = projectService.getProject(projectId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Create a new project")
    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@Valid @RequestBody ProjectCreateRequest request) {
        var response = projectService.createProject(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Update an existing project by ID")
    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(@Valid @RequestBody ProjectUpdateRequest request, @PathVariable("id") Long projectId) {
        var response = projectService.updateProject(projectId, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete project by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable("id") Long projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.noContent().build();
    }
}
