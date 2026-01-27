package com.hackathon.otj_logger.repository;

import com.hackathon.otj_logger.model.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository<Coach, Long> {
}

