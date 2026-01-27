package com.hackathon.otj_logger.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "holiday")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Holiday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ApprenticeID", nullable = false)
    private Long apprenticeId;

    @Column(name = "HolidayDays")
    private Integer holidayDays;

    @Column(name = "HolidayMode")
    private Boolean holidayMode;
}
