package com.example.hr_system.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "staffNumber", nullable = false)
    private String staff_number;
    @Column(name = "ch_FullName", nullable = false)
    private String chinese_FullName;
    @Column(name = "en_Surname", nullable = false)
    private String english_Surname;
    @Column(name = "en_Given_Name", nullable = false)
    private String english_Given_Name;

    @Column(name = "employment_date", nullable = false)
    private Date employment_date;

    @Column(name = "activeStatus", nullable = false)
    private Boolean active_Status;

    @Column(name = "resignation_date", nullable = true)
    private Date resignation_date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id")
    private Position position;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shop_id")
    private Shop shop;

}
