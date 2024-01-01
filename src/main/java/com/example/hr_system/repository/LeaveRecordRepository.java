package com.example.hr_system.repository;

import com.example.hr_system.entity.Employee;
import com.example.hr_system.entity.LeaveRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRecordRepository extends JpaRepository<LeaveRecord, Long> {
    @Query(nativeQuery = true, value = "SELECT * from leave_record l where l.employee_id = ?1")
    public Page<LeaveRecord> findAllRecordByEmployeeID(Long employeeID, Pageable pageable);
}
