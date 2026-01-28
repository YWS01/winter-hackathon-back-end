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
    private Long id;

    private String course;

    private Boolean isOffTheJob;

    private Integer totalOTJHours;
}
