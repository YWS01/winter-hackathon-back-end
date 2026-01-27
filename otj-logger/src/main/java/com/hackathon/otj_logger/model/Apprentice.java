package com.hackathon.otj_logger.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@Entity
@Table(name = "users_apprentice")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Apprentice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CoachID")
    private Long coachId;

    @Column(name = "Name")
    private String name;

    @Column(name = "OTJWeeklyRate")
    private Integer otjWeeklyRate;

    @Column(name = "CourseStartDate")
    private LocalDate courseStartDate;

    @Column(name = "CourseID")
    private Long courseId;
}
