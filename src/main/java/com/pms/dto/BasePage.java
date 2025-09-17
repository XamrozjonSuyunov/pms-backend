package com.pms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@ToString
public abstract class BasePage {

    protected int page = 0;
    protected int size = 10;
    @Schema(allowableValues = {"id", "name", "type", "status", "workingDays", "starDate", "endDate", "revenue"})
    protected String sortBy;
    @Schema(allowableValues = {"asc", "desc"})
    protected String direction;

    public Pageable toPageable() {
        Sort sort = Sort.by(Sort.Direction.DESC, "datetimeCreated");

        if (sortBy != null && !sortBy.isBlank()) {
            Sort.Direction dir = "asc".equalsIgnoreCase(direction) ? Sort.Direction.ASC : Sort.Direction.DESC;
            sort = Sort.by(dir, sortBy);
        }

        return PageRequest.of(page, size, sort);
    }
}
