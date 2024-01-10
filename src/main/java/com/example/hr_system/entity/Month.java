package com.example.hr_system.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Month {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "month")
    private long month;
}
