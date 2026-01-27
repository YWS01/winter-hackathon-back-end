package com.hackathon.otj_logger.repository;

import com.hackathon.otj_logger.model.JournalEntryKsb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JournalEntryKsbRepository extends JpaRepository<JournalEntryKsb, Long> {
    List<JournalEntryKsb> findByJournalEntryId(Long journalId);
    Optional<JournalEntryKsb> findByJournalEntryIdAndKsbId(Long journalId, Long ksbId);
    List<JournalEntryKsb> findByKsbId(Long ksbId);
}

