package com.pms.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "plans")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer year;

    private Long revenue;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime datetimeCreated;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime datetimeUpdated;
}
