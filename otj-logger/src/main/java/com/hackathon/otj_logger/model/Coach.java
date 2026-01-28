package com.hackathon.otj_logger.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users_coach")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
