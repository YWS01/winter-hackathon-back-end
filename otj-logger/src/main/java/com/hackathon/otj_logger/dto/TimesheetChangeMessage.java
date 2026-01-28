package com.hackathon.otj_logger.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimesheetChangeMessage {
    private String action; // created, updated, deleted
    private TimesheetDTO timesheet;
}

