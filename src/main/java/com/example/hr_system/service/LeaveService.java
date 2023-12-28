package com.example.hr_system.service;

import com.example.hr_system.entity.LeaveBalance;
import com.example.hr_system.entity.LeaveType;
import com.example.hr_system.payload.request.leave.EmployeeLeaveBalanceDTO;

import java.util.List;

public interface LeaveService {

    List<LeaveType> getAllLeaveType();

    void createNewLeaveBalance(Long EmployeeId, EmployeeLeaveBalanceDTO employeeLeaveBalanceDTO);

    void createNewLeaveBalanceForNewEmployee(long EmployeeId);
}
