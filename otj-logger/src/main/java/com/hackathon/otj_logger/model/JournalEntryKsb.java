package com.hackathon.otj_logger.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "journal_entry_ksbs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JournalEntryKsb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "KSBID", nullable = false)
    private Long ksbId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JournalID", nullable = false)
    private JournalEntry journalEntry;
}
