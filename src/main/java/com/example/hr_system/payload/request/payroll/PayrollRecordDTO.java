package com.example.hr_system.payload.request.payroll;

import lombok.Data;

@Data
public class PayrollRecordDTO {

    private Long employeeID;

    private Long basisSalary;

    private Long bonus;

    private Long employee_mpf;

    private Long employer_mpf;

    private Long net_payment;

    private Long total_amount;
}
