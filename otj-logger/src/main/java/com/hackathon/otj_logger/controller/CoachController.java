package com.hackathon.otj_logger.controller;

import com.hackathon.otj_logger.model.Coach;
import com.hackathon.otj_logger.repository.CoachRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/coaches")
public class CoachController {

    private final CoachRepository repository;

    public CoachController(CoachRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Coach> list() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coach> get(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Coach> create(@RequestBody Coach coach) {
        Coach saved = repository.save(coach);
        return ResponseEntity.created(URI.create("/coaches/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coach> update(@PathVariable Long id, @RequestBody Coach coach) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        coach.setId(id);
        Coach saved = repository.save(coach);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

