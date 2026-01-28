package com.hackathon.otj_logger.repository;

import com.hackathon.otj_logger.model.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
    Optional<Holiday> findByApprenticeId(Long apprenticeId);
}
