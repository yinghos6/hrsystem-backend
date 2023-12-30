package com.example.hr_system.repository;

import com.example.hr_system.entity.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveStatusRepository extends JpaRepository<LeaveStatus, Long> {
}
