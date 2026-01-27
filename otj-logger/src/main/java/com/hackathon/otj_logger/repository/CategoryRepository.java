package com.hackathon.otj_logger.repository;

import com.hackathon.otj_logger.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

