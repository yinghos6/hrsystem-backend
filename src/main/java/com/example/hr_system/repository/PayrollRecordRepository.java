package com.example.hr_system.repository;

import com.example.hr_system.entity.LeaveBalance;
import com.example.hr_system.entity.PayrollRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayrollRecordRepository extends JpaRepository<PayrollRecord, Long> {

    @Query(nativeQuery = true, value = "SELECT * from payroll_record p where p.employee_id = ?1")
    public List<PayrollRecord> findPayrollRecordByEmployeeID(Long employeeID);
}
