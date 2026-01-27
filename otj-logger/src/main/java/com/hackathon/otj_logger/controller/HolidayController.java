package com.hackathon.otj_logger.controller;

import com.hackathon.otj_logger.model.Holiday;
import com.hackathon.otj_logger.repository.HolidayRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/holidays")
public class HolidayController {

    private final HolidayRepository repository;

    public HolidayController(HolidayRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Holiday> list() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Holiday> get(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Holiday> create(@RequestBody Holiday holiday) {
        if (holiday.getApprenticeId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Holiday saved = repository.save(holiday);
        return ResponseEntity.created(URI.create("/holidays/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Holiday> update(@PathVariable Long id, @RequestBody Holiday holiday) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        holiday.setId(id);
        Holiday saved = repository.save(holiday);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

