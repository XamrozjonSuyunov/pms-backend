package com.pms.entity;

import com.pms.enums.ProjectStatus;
import com.pms.enums.ProjectType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ProjectType type;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    private Long revenue;

    private LocalDate startDate;
    private LocalDate endDate;
    private Integer workingDays;

    private Integer plannedRate;
    private Integer actualRate;

    private String pmName;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime datetimeCreated;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime datetimeUpdated;

}
