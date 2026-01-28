package com.hackathon.otj_logger.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimesheetDTO {
    private Long id;
    private Long journalId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long apprenticeId;
    // computed on read: total worked hours for this timesheet entry
    private Double workedHours;
}
