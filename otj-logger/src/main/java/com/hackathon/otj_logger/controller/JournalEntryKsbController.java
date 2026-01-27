package com.hackathon.otj_logger.controller;

import com.hackathon.otj_logger.dto.JournalEntryKsbDTO;
import com.hackathon.otj_logger.model.JournalEntryKsb;
import com.hackathon.otj_logger.model.JournalEntry;
import com.hackathon.otj_logger.model.Ksb;
import com.hackathon.otj_logger.repository.JournalEntryKsbRepository;
import com.hackathon.otj_logger.repository.JournalEntryRepository;
import com.hackathon.otj_logger.repository.KsbRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
public class JournalEntryKsbController {

    private final JournalEntryKsbRepository repository;
    private final JournalEntryRepository journalRepository;
    private final KsbRepository ksbRepository;

    public JournalEntryKsbController(JournalEntryKsbRepository repository,
                                     JournalEntryRepository journalRepository,
                                     KsbRepository ksbRepository) {
        this.repository = repository;
        this.journalRepository = journalRepository;
        this.ksbRepository = ksbRepository;
    }

    private JournalEntryKsbDTO toDto(JournalEntryKsb j) {
        return JournalEntryKsbDTO.builder()
                .id(j.getId())
                .ksbId(j.getKsbId())
                .journalId(j.getJournalEntry() != null ? j.getJournalEntry().getId() : null)
                .build();
    }

    @GetMapping("/entries/{entryId}/ksbs")
    public ResponseEntity<List<JournalEntryKsbDTO>> listForEntry(@PathVariable Long entryId) {
        if (!journalRepository.existsById(entryId)) return ResponseEntity.notFound().build();
        List<JournalEntryKsbDTO> items = repository.findByJournalEntryId(entryId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(items);
    }

    @PostMapping("/entries/{entryId}/ksbs")
    public ResponseEntity<JournalEntryKsbDTO> attach(@PathVariable Long entryId, @RequestBody Map<String, Long> body) {
        Long ksbId = body.get("ksbId");
        if (ksbId == null) return ResponseEntity.badRequest().build();
        JournalEntry journal = journalRepository.findById(entryId).orElse(null);
        if (journal == null) return ResponseEntity.notFound().build();
        Ksb ksb = ksbRepository.findById(ksbId).orElse(null);
        if (ksb == null) return ResponseEntity.badRequest().build();

        // prevent duplicate
        if (repository.findByJournalEntryIdAndKsbId(entryId, ksbId).isPresent()) {
            return ResponseEntity.status(409).build();
        }

        JournalEntryKsb j = JournalEntryKsb.builder()
                .ksbId(ksbId)
                .journalEntry(journal)
                .build();
        JournalEntryKsb saved = repository.save(j);
        return ResponseEntity.created(URI.create("/entries/" + entryId + "/ksbs/" + saved.getKsbId())).body(toDto(saved));
    }

    @DeleteMapping("/entries/{entryId}/ksbs/{ksbId}")
    public ResponseEntity<Void> detach(@PathVariable Long entryId, @PathVariable Long ksbId) {
        return repository.findByJournalEntryIdAndKsbId(entryId, ksbId)
                .map(relation -> {
                    repository.deleteById(relation.getId());
                    return ResponseEntity.noContent().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/ksbs/{ksbId}/entries")
    public ResponseEntity<List<JournalEntryKsbDTO>> listForKsb(@PathVariable Long ksbId) {
        if (!ksbRepository.existsById(ksbId)) return ResponseEntity.notFound().build();
        List<JournalEntryKsbDTO> items = repository.findByKsbId(ksbId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(items);
    }
}

