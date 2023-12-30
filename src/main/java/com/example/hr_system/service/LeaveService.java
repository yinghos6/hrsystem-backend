package com.example.hr_system.service;

import com.example.hr_system.entity.LeaveBalance;
import com.example.hr_system.entity.LeaveRecord;
import com.example.hr_system.entity.LeaveStatus;
import com.example.hr_system.entity.LeaveType;
import com.example.hr_system.payload.request.leave.EmployeeLeaveBalanceDTO;
import com.example.hr_system.payload.request.leave.EmployeeLeaveRecordDTO;

import java.util.List;

public interface LeaveService {

    List<LeaveType> getAllLeaveType();

    List<LeaveStatus> getAllLeaveStatus();

    void createNewLeaveBalance(Long EmployeeId, EmployeeLeaveBalanceDTO employeeLeaveBalanceDTO);

    void createNewLeaveBalanceForNewEmployee(long EmployeeId);

    LeaveRecord createNewLeaveRecord(Long EmployeeId, EmployeeLeaveRecordDTO employeeLeaveRecordDTO);
}
