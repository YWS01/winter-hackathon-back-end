package com.hackathon.otj_logger.repository;

import com.hackathon.otj_logger.model.Timesheet;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {

    @Override
    @EntityGraph(attributePaths = {"journalEntry"})
    List<Timesheet> findAll();

    @EntityGraph(attributePaths = {"journalEntry"})
    List<Timesheet> findByJournalEntryId(Long journalId);

    @Override
    @EntityGraph(attributePaths = {"journalEntry"})
    Optional<Timesheet> findById(Long id);
}
