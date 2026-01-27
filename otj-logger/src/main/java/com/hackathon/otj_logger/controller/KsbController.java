package com.hackathon.otj_logger.controller;

import com.hackathon.otj_logger.model.Ksb;
import com.hackathon.otj_logger.repository.KsbRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/ksbs")
public class KsbController {

    private final KsbRepository repository;

    public KsbController(KsbRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Ksb> list() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ksb> get(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Ksb> create(@RequestBody Ksb ksb) {
        Ksb saved = repository.save(ksb);
        return ResponseEntity.created(URI.create("/ksbs/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ksb> update(@PathVariable Long id, @RequestBody Ksb ksb) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        ksb.setId(id);
        Ksb saved = repository.save(ksb);
        return ResponseEntity.ok(saved);
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

