package com.hackathon.otj_logger.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Table(name = "timesheets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Timesheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Replace raw journalId with a JPA relationship to JournalEntry
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JOURNALID", nullable = false)
    private JournalEntry journalEntry;

    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    // If workedHours is required in code, add column WORKED_HOURS in DB or compute on the fly.
}
