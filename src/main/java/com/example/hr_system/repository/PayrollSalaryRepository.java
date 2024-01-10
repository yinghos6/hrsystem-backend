package com.example.hr_system.repository;

import com.example.hr_system.entity.PayrollSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayrollSalaryRepository extends JpaRepository<PayrollSalary, Long> {
}
