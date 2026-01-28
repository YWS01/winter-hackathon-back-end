package com.hackathon.otj_logger.service;

import com.hackathon.otj_logger.dto.HolidayProjectionDTO;
import com.hackathon.otj_logger.model.Apprentice;
import com.hackathon.otj_logger.model.Timesheet;
import com.hackathon.otj_logger.model.Holiday;
import com.hackathon.otj_logger.repository.ApprenticeRepository;
import com.hackathon.otj_logger.repository.TimesheetRepository;
import com.hackathon.otj_logger.repository.HolidayRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class HolidayService {

    private final ApprenticeRepository apprenticeRepository;
    private final TimesheetRepository timesheetRepository;
    private final HolidayRepository holidayRepository;

    public HolidayService(ApprenticeRepository apprenticeRepository, TimesheetRepository timesheetRepository, HolidayRepository holidayRepository) {
        this.apprenticeRepository = apprenticeRepository;
        this.timesheetRepository = timesheetRepository;
        this.holidayRepository = holidayRepository;
    }

    public HolidayProjectionDTO previewHolidayProjection(Long apprenticeId, LocalDate start, LocalDate end) {
        Apprentice apprentice = apprenticeRepository.findById(apprenticeId)
                .orElseThrow(() -> new IllegalArgumentException("Apprentice not found: " + apprenticeId));

        int weeklyRate = apprentice.getOtjWeeklyRate() != null ? apprentice.getOtjWeeklyRate() : 0;

        long inclusiveDays = ChronoUnit.DAYS.between(start, end) + 1; // inclusive
        double weeksOff = inclusiveDays / 7.0;
        double expectedHours = weeksOff * weeklyRate;

        LocalDateTime startDt = start.atStartOfDay();
        LocalDateTime endDt = end.plusDays(1).atStartOfDay(); // exclusive upper bound

        List<Timesheet> timesheets = timesheetRepository.findByJournalEntryApprenticeIdAndStartDateBetween(apprenticeId, startDt, endDt);

        // default behavior: include all timesheets and use weekly-based expectedHours
        double logged = timesheets.stream()
                // compute duration even if endDate is null (treat as now)
                .filter(t -> t.getStartDate() != null)
                .mapToDouble(t -> {
                    LocalDateTime s = t.getStartDate();
                    LocalDateTime e = t.getEndDate() != null ? t.getEndDate() : LocalDateTime.now();
                    return Duration.between(s, e).toMinutes() / 60.0;
                })
                .sum();

        // check for holiday settings for this apprentice
        Holiday holiday = holidayRepository.findByApprenticeId(apprenticeId).orElse(null);
        Boolean holidayMode = holiday != null ? holiday.getHolidayMode() : null;
        Integer holidayDays = holiday != null ? holiday.getHolidayDays() : null;

        if (Boolean.TRUE.equals(holidayMode) && holidayDays != null) {
            // compute expected hours based on number of holiday days and average daily hours (weeklyRate / 5)
            double dailyHours = weeklyRate / 5.0;
            expectedHours = holidayDays * dailyHours;
            // when in holidayMode, exclude open timesheets (endDate == null) from logged hours to avoid counting ongoing work
            logged = timesheets.stream()
                    .filter(t -> t.getStartDate() != null && t.getEndDate() != null)
                    .mapToDouble(t -> Duration.between(t.getStartDate(), t.getEndDate()).toMinutes() / 60.0)
                    .sum();
        }

        double deficit = expectedHours - logged;

        return HolidayProjectionDTO.builder()
                .apprenticeId(apprenticeId)
                .startDate(start)
                .endDate(end)
                .weeksOff(weeksOff)
                .expectedHoursDuringHoliday(expectedHours)
                .loggedHoursDuringHoliday(logged)
                .projectionDeficit(deficit)
                .holidayMode(holidayMode)
                .holidayDays(holidayDays)
                .build();
    }
}
