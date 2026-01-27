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
    @Column(name = "ID")
    private Long id;

    // Replace raw journalId with a JPA relationship to JournalEntry
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JournalID", nullable = false)
    private JournalEntry journalEntry;

    @Column(name = "StartDate")
    private LocalDateTime startDate;

    @Column(name = "EndDate")
    private LocalDateTime endDate;

    // ApprenticeID removed — derive apprentice via the JournalEntry referenced by journalEntry
}
