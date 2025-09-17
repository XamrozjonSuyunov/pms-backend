package com.pms.repository;

import com.pms.dto.ProjectStatsResponse;
import com.pms.dto.RevenueShareProjection;
import com.pms.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
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

    @Query(value = "SELECT * FROM projects WHERE status in (:statuses) AND (CAST(strftime('%Y', start_date / 1000, 'unixepoch') as INTEGER) = :year OR CAST(strftime('%Y', end_date / 1000, 'unixepoch') as INTEGER) = :year)", nativeQuery = true)
    List<Project> getProjectsByStatusInAndYear(List<String> statuses, Integer year);

    @Query(value = """
        SELECT 
            type,
            ROUND(100.0 * SUM(revenue) / (
                SELECT SUM(revenue)
                FROM projects
                WHERE CAST(strftime('%Y', start_date / 1000, 'unixepoch') AS INTEGER) = :year
                   OR CAST(strftime('%Y', end_date / 1000, 'unixepoch') AS INTEGER) = :year
            ), 2) AS present,
            GROUP_CONCAT(name) AS projectNames
        FROM projects
        WHERE CAST(strftime('%Y', start_date / 1000, 'unixepoch') AS INTEGER) = :year
           OR CAST(strftime('%Y', end_date / 1000, 'unixepoch') AS INTEGER) = :year
        GROUP BY type
        ORDER BY SUM(revenue) DESC
        """, nativeQuery = true)
    List<RevenueShareProjection> findRevenueShareByYear(Integer year);

    @Query(value = """
        SELECT SUM(revenue) FROM projects 
        WHERE status = ?1 AND (CAST(strftime('%Y', start_date / 1000, 'unixepoch') as INTEGER) = :year OR CAST(strftime('%Y', end_date / 1000, 'unixepoch') as INTEGER) = :year)
    """, nativeQuery = true)
    Long getRevenueByStatusAndYear(String status, Integer year);

    @Query(value = "SELECT * FROM projects WHERE status = :status AND (CAST(strftime('%Y', start_date / 1000, 'unixepoch') as INTEGER) = :year OR CAST(strftime('%Y', end_date / 1000, 'unixepoch') as INTEGER) = :year)", nativeQuery = true)
    List<Project> getProjectsByStatusAndYear(String status, Integer year);


    Page<Project> findAll(Specification<Project> filter, Pageable pageable);
}
