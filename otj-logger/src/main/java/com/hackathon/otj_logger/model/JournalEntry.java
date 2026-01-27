package com.hackathon.otj_logger.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Table(name = "journal_entries")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JournalEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ApprenticeID", nullable = true)
    private Long apprenticeId;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "Description", nullable = false, length = 2000)
    private String description;

    @Column(name = "Creation")
    private LocalDateTime creation;

    @Column(name = "LastUpdated")
    private LocalDateTime lastUpdated;

    @Column(name = "CheckedByCoachStatus")
    private Integer checkedByCoachStatus;

    @Column(name = "CheckedbyCoachID")
    private Long checkedbyCoachId;

    @Column(name = "CheckedbyCoachTime")
    private LocalDateTime checkedbyCoachTime;

    @Column(name = "CategoryID")
    private Long categoryId;

    @PrePersist
    void onCreate() {
        if (this.creation == null) this.creation = LocalDateTime.now();
        if (this.lastUpdated == null) this.lastUpdated = this.creation;
    }

    @PreUpdate
    void onUpdate() {
        this.lastUpdated = LocalDateTime.now();
    }
}
