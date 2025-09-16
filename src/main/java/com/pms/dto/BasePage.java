package com.pms.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@ToString
public abstract class BasePage {

    protected int page = 0;
    protected int size = 10;

    public Pageable toPageable() {
        return PageRequest.of(page, size);
    }
}
