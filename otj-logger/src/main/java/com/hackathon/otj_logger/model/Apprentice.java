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
    private Long id;

    @Column(name = "COACHID")
    private Long coachId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "OTJWEEKLY_RATE")
    private Integer otjWeeklyRate;

    @Column(name = "COURSE_START_DATE")
    private LocalDate courseStartDate;

    @Column(name = "COURSEID")
    private Long courseId;
}
