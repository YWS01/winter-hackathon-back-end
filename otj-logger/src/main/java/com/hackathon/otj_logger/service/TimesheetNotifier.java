package com.hackathon.otj_logger.service;

import com.hackathon.otj_logger.dto.TimesheetChangeMessage;
import com.hackathon.otj_logger.dto.TimesheetDTO;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class TimesheetNotifier {

    private final SimpMessagingTemplate messagingTemplate;

    public TimesheetNotifier(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void notifyTimesheetChange(TimesheetDTO timesheet, String action) {
        TimesheetChangeMessage msg = TimesheetChangeMessage.builder()
                .action(action)
                .timesheet(timesheet)
                .build();
        // broadcast to topic so FE can subscribe to /topic/timesheets
        messagingTemplate.convertAndSend("/topic/timesheets", msg);
    }
}

