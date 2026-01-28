package com.hackathon.otj_logger.controller;

import com.hackathon.otj_logger.model.Apprentice;
import com.hackathon.otj_logger.repository.ApprenticeRepository;
import com.hackathon.otj_logger.service.HolidayService;
import com.hackathon.otj_logger.dto.HolidayProjectionDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/apprentices")
public class ApprenticeController {

    private final ApprenticeRepository repository;
    private final HolidayService holidayService;

    public ApprenticeController(ApprenticeRepository repository, HolidayService holidayService) {
        this.repository = repository;
        this.holidayService = holidayService;
    }

    @GetMapping("/{id}/holiday-projection")
    public ResponseEntity<HolidayProjectionDTO> holidayProjection(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end
    ) {
        HolidayProjectionDTO dto = holidayService.previewHolidayProjection(id, start, end);
        return ResponseEntity.ok(dto);
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
