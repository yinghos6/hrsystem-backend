package com.example.hr_system.repository;

import com.example.hr_system.entity.LeaveBalance;
import com.example.hr_system.entity.LeaveRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance, Long> {

    @Query(nativeQuery = true, value = "SELECT * from leave_balance l where l.employee_id = ?1")
    public LeaveBalance findLeaveBalanceByEmployeeID(Long employeeID);
}
