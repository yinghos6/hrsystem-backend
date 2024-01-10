package com.example.hr_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@Entity
@Table(name = "payroll_record")
public class PayrollRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "year_id")
    private Year year;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "month_id")
    private Month month;

    @Column(name = "basis_salary")
    private long basisSalary;

    @Column(name = "bonus")
    private long bonus;

    @Column(name = "total_amount")
    private long totalAmount;

    @Column(name = "employee_contribution")
    private long employeeContribution;

    @Column(name = "employer_contribution")
    private long employerContribution;

    @Column(name = "net_payment")
    private long netPayment;

    @Column(name = "created_date", nullable = false)
    @CreationTimestamp
    private Date createdDate;

    @Column(name = "updated_date")
    @UpdateTimestamp
    private Date updatedDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    @JsonIgnore
    private Employee employee;
}
