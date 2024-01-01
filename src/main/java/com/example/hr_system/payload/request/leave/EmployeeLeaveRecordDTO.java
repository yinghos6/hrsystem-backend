package com.example.hr_system.payload.request.leave;

import lombok.Data;

import java.util.Date;

@Data
public class EmployeeLeaveRecordDTO {

    private Long employeeID;

    private Long leaveTypeID;

    private Long leaveStatusID;

    private String leaveYear;

    private Date leaveStartDate;

    private Date leaveEndDate;

    private Long leave_counted_days;

}
