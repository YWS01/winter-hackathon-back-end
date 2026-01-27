package com.hackathon.otj_logger.controller;

import com.hackathon.otj_logger.model.Course;
import com.hackathon.otj_logger.repository.CourseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/courses")
public class CourseController {

    private final CourseRepository repository;

    public CourseController(CourseRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Course> list() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> get(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody Course course) {
        Course saved = repository.save(course);
        return ResponseEntity.created(URI.create("/courses/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody Course course) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        course.setId(id);
        Course saved = repository.save(course);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

