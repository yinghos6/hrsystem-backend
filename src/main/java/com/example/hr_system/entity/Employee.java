package com.example.hr_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
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
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date employment_date;

    @Column(name = "activeStatus", nullable = false)
    private Boolean active_Status;

    @Column(name = "resignation_date", nullable = true)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date resignation_date;

    @Column(name = "created_Time", nullable = false)
    @CurrentTimestamp
    private Date createdTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id")
    private Position position;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LeaveBalance> leaveBalances = new HashSet<>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LeaveRecord> leaveRecords = new HashSet<>();



}
