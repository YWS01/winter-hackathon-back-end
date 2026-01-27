package com.hackathon.otj_logger.controller;

import com.hackathon.otj_logger.model.Category;
import com.hackathon.otj_logger.repository.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository repository;

    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Category> list() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> get(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category) {
        Category saved = repository.save(category);
        return ResponseEntity.created(URI.create("/categories/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        category.setId(id);
        Category saved = repository.save(category);
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

