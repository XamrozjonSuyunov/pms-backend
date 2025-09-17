package com.pms.dto;

import com.pms.enums.ProjectStatus;
import com.pms.enums.ProjectType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectFilter extends BasePage {
    private String name;
    private ProjectType type;
    private ProjectStatus status;
}
