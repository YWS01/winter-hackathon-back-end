package com.hackathon.otj_logger.controller;

import com.hackathon.otj_logger.model.Apprentice;
import com.hackathon.otj_logger.repository.ApprenticeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/apprentices")
public class ApprenticeController {

    private final ApprenticeRepository repository;

    public ApprenticeController(ApprenticeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Apprentice> list() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Apprentice> get(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Apprentice> create(@RequestBody Apprentice apprentice) {
        Apprentice saved = repository.save(apprentice);
        return ResponseEntity.created(URI.create("/apprentices/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Apprentice> update(@PathVariable Long id, @RequestBody Apprentice apprentice) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        apprentice.setId(id);
        Apprentice saved = repository.save(apprentice);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

