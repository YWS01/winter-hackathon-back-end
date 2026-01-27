package com.hackathon.otj_logger.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "course")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Course")
    private String course;

    @Column(name = "IsOffTheJob")
    private Boolean isOffTheJob;

    @Column(name = "TotalOTJHours")
    private Integer totalOTJHours;
}
