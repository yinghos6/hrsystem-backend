package com.example.hr_system.service;

import com.example.hr_system.entity.PayrollRecord;
import com.example.hr_system.payload.request.payroll.PayrollRecordDTO;

import java.util.List;

public interface PayrollService {


    void createNewPayrollSalaryForNewEmployee(long EmployeeId, long PositionID);


    void createNewPayrollRecord(Long EmployeeID, PayrollRecordDTO payrollRecordDTO);

    List<PayrollRecord> findAllPayrollRecordByEmployeeID(Long employeeID);

}
