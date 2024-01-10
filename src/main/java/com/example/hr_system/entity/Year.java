package com.example.hr_system.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Year {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "year")
    private long year;
}
