package com.hackathon.otj_logger.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JournalEntryKsbDTO {
    private Long id;
    private Long ksbId;
    private Long journalId;
}

