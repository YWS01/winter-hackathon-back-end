package com.hackathon.otj_logger.controller;

import com.hackathon.otj_logger.dto.TimesheetDTO;
import com.hackathon.otj_logger.model.JournalEntry;
import com.hackathon.otj_logger.model.Timesheet;
import com.hackathon.otj_logger.repository.JournalEntryRepository;
import com.hackathon.otj_logger.repository.TimesheetRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/timesheets")
public class TimesheetController {

    private final TimesheetRepository repository;
    private final JournalEntryRepository journalRepository;

    public TimesheetController(TimesheetRepository repository, JournalEntryRepository journalRepository) {
        this.repository = repository;
        this.journalRepository = journalRepository;
    }

    private TimesheetDTO toDto(Timesheet t) {
        TimesheetDTO dto = TimesheetDTO.builder()
                .id(t.getId())
                .journalId(t.getJournalEntry() != null ? t.getJournalEntry().getId() : null)
                .startDate(t.getStartDate())
                .endDate(t.getEndDate())
                .apprenticeId(t.getJournalEntry() != null ? t.getJournalEntry().getApprenticeId() : null)
                .build();
        return dto;
    }

    @GetMapping
    public List<TimesheetDTO> list(@RequestParam(required = false) Long journalId) {
        List<Timesheet> items;
        if (journalId != null) {
            items = repository.findByJournalEntryId(journalId);
        } else {
            items = repository.findAll();
        }
        return items.stream().map(this::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimesheetDTO> get(@PathVariable Long id) {
        return repository.findById(id)
                .map(this::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TimesheetDTO> create(@RequestBody TimesheetDTO dto) {
        if (dto.getJournalId() == null) {
            return ResponseEntity.badRequest().build();
        }
        JournalEntry journal = journalRepository.findById(dto.getJournalId()).orElse(null);
        if (journal == null) return ResponseEntity.badRequest().build();

        Timesheet t = Timesheet.builder()
                .journalEntry(journal)
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();
        Timesheet saved = repository.save(t);
        return ResponseEntity.created(URI.create("/timesheets/" + saved.getId())).body(toDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimesheetDTO> update(@PathVariable Long id, @RequestBody TimesheetDTO dto) {
        java.util.Optional<Timesheet> opt = repository.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Timesheet existing = opt.get();

        if (dto.getJournalId() != null) {
            java.util.Optional<JournalEntry> journalOpt = journalRepository.findById(dto.getJournalId());
            if (journalOpt.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            existing.setJournalEntry(journalOpt.get());
        }

        existing.setStartDate(dto.getStartDate());
        existing.setEndDate(dto.getEndDate());
        Timesheet saved = repository.save(existing);
        return ResponseEntity.ok(toDto(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
