package com.pms.repository;

import com.pms.dto.ProjectStatsResponse;
import com.pms.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("select p from Project p order by p.datetimeCreated desc")
    Page<Project> getProjectsOrderByDatetimeCreated(Pageable pageable);

    @Query("""
        SELECT new com.pms.dto.ProjectStatsResponse(
            COUNT(p),
            COALESCE(SUM(CASE WHEN p.status = 'DELAYED' THEN 1 ELSE 0 END), 0),
            COALESCE(SUM(CASE WHEN p.status = 'ONGOING' THEN 1 ELSE 0 END), 0),
            COALESCE(SUM(CASE WHEN p.status = 'DROPPED' THEN 1 ELSE 0 END), 0),
            COALESCE(SUM(CASE WHEN p.status = 'COMPLETED' THEN 1 ELSE 0 END), 0)
        )
        FROM Project p
    """)
    ProjectStatsResponse getProjectStats();
}
