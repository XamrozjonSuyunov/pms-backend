package com.pms.specification;

import com.pms.dto.ProjectFilter;
import com.pms.entity.Project;
import org.springframework.data.jpa.domain.Specification;

public class ProjectSpecification {
    public static Specification<Project> filter(ProjectFilter filter) {
        return (root, query, cb) -> {
            var predicates = cb.conjunction();

            // search by name
            if (filter.getName() != null && !filter.getName().isBlank()) {
                predicates = cb.and(predicates,
                        cb.like(cb.lower(root.get("name")), "%" + filter.getName().toLowerCase() + "%"));
            }

            // filter by type
            if (filter.getType() != null) {
                predicates = cb.and(predicates,
                        cb.equal(root.get("type"), filter.getType()));
            }

            // filter by status
            if (filter.getStatus() != null) {
                predicates = cb.and(predicates,
                        cb.equal(root.get("status"), filter.getStatus()));
            }

            return predicates;
        };
    }
}
