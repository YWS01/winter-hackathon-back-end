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
    private Long id;

    @Column(name = "APPRENTICEID", nullable = false)
    private Long apprenticeId;

    @Column(name = "HOLIDAY_DAYS")
    private Integer holidayDays;

    @Column(name = "HOLIDAY_MODE")
    private Boolean holidayMode;
}
