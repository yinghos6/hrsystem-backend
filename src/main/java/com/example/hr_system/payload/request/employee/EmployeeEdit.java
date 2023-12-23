package com.example.hr_system.payload.request.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeeEdit {

    private Long edited_employee_id;

    private String edited_staff_number;

    private String edited_chi_fullName;

    private String edited_eng_surname;

    private String edited_given_name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date edited_employment_date;

    private Long edited_departmentId;

    private Long edited_shopId;

    private Long edited_positionId;
}
