package com.hackathon.otj_logger.repository;

import com.hackathon.otj_logger.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}

