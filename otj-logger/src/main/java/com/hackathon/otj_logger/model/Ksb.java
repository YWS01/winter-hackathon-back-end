package com.hackathon.otj_logger.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ksbs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ksb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Type")
    private String type;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description", length = 2000)
    private String description;
}
