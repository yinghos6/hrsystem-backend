package com.example.hr_system.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Entity
@Table(name = "leave_balance")
public class LeaveBalance {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(name = "leave_balance_year", nullable = false)
        private String leaveBalanceYear;

        @Column(name = "balance_annual_leave", nullable = false)
        private long balanceAnnualLeave;

        @Column(name = "balance_sick_leave", nullable = false)
        private long balanceSickLeave;

        @Column(name = "balance_special_leave", nullable = false)
        private long balanceSpecialLeave;

        @Column(name = "craeted_date", nullable = true)
        @CurrentTimestamp
        private Date createdDate;

        @Column(name = "updated_date", nullable = true)
        private Date updatedDate;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "employee_id", nullable = false)
        private Employee employee;

}
