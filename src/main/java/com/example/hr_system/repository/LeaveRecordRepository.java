package com.example.hr_system.repository;

import com.example.hr_system.entity.LeaveRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRecordRepository extends JpaRepository<LeaveRecord, Long> {
}
