package com.hackathon.otj_logger.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HolidayProjectionDTO {
    private Long apprenticeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double weeksOff;
    private double expectedHoursDuringHoliday;
    private double loggedHoursDuringHoliday;
    private double projectionDeficit;

    // new fields to surface holiday settings
    private Boolean holidayMode;
    private Integer holidayDays;
}
