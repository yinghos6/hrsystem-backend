package com.example.hr_system.payload.request.leave;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class EmployeeLeaveBalanceDTO {

    private Long employeeID;

    private String leaveYear;

    private Long balanceAL;

    private Long balanceSL;

    private Long balanceSPL;



}


