package com.hackathon.otj_logger.controller;

import com.hackathon.otj_logger.model.JournalEntry;
import com.hackathon.otj_logger.repository.JournalEntryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

import jakarta.annotation.Nonnull;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
@RequestMapping("/entries")
public class JournalEntryController {

    private final JournalEntryRepository repository;

    public JournalEntryController(JournalEntryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<JournalEntry> list() {
        return repository.findAll();
    }

    @PostMapping
    public @Nonnull ResponseEntity<JournalEntry> create(@RequestBody JournalEntry entry) {
        JournalEntry saved = repository.save(entry);
        return ResponseEntity.created(URI.create("/entries/" + saved.getId())).body(saved);
    }

    @DeleteMapping("/{id}")
    public @Nonnull ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
