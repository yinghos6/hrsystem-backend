package com.example.hr_system.service;

import com.example.hr_system.payload.request.payroll.PayrollRecordDTO;

public interface PayrollService {


    void createNewPayrollSalaryForNewEmployee(long EmployeeId, long PositionID);


    void createNewPayrollRecord(Long EmployeeID, PayrollRecordDTO payrollRecordDTO);
}
