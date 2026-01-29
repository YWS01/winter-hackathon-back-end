package com.hackathon.otj_logger.controller;

import com.hackathon.otj_logger.dto.TimesheetDTO;
import com.hackathon.otj_logger.model.JournalEntry;
import com.hackathon.otj_logger.model.Timesheet;
import com.hackathon.otj_logger.repository.JournalEntryRepository;
import com.hackathon.otj_logger.repository.TimesheetRepository;
import com.hackathon.otj_logger.service.TimesheetNotifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/timesheets")
public class TimesheetController {

    private final TimesheetRepository repository;
    private final JournalEntryRepository journalRepository;
    private final TimesheetNotifier notifier;

    public TimesheetController(TimesheetRepository repository, JournalEntryRepository journalRepository, TimesheetNotifier notifier) {
        this.repository = repository;
        this.journalRepository = journalRepository;
        this.notifier = notifier;
    }

    private TimesheetDTO toDto(Timesheet t) {
        TimesheetDTO dto = TimesheetDTO.builder()
                .id(t.getId())
                .journalId(t.getJournalEntry() != null ? t.getJournalEntry().getId() : null)
                .startDate(t.getStartDate())
                .endDate(t.getEndDate())
                .apprenticeId(t.getJournalEntry() != null ? t.getJournalEntry().getApprenticeId() : null)
                .build();

        // compute-on-read: workedHours is computed from start/end (if end is null, compute until now)
        Double worked = 0.0;
        LocalDateTime s = t.getStartDate();
        LocalDateTime e = t.getEndDate();
        if (s != null) {
            if (e != null) {
                worked = Duration.between(s, e).toMinutes() / 60.0;
            } else {
                worked = Duration.between(s, LocalDateTime.now()).toMinutes() / 60.0;
            }
        }
        dto.setWorkedHours(worked);

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
        java.util.Optional<Timesheet> opt = repository.findById(id);
        if (opt.isPresent()) {
            TimesheetDTO dto = toDto(opt.get());
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
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
        TimesheetDTO out = toDto(saved);
        // notify clients about the new timesheet
        notifier.notifyTimesheetChange(out, "created");
        return ResponseEntity.created(URI.create("/timesheets/" + saved.getId())).body(out);
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
        TimesheetDTO out = toDto(saved);
        // notify clients about the updated timesheet
        notifier.notifyTimesheetChange(out, "updated");
        return ResponseEntity.ok(out);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        // notify clients about the deleted timesheet (send only id and action)
        notifier.notifyTimesheetChange(TimesheetDTO.builder().id(id).build(), "deleted");
        return ResponseEntity.noContent().build();
    }
}
