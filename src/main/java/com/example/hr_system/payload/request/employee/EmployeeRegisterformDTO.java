package com.example.hr_system.payload.request.employee;

import com.example.hr_system.entity.Department;
import com.example.hr_system.entity.Position;
import com.example.hr_system.entity.Shop;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeeRegisterformDTO {

        private String staff_number;

        private String chi_fullName;

        private String eng_surname;

        private String eng_given_name;

        @JsonFormat(pattern = "dd/MM/yyyy")
        private Date employment_date;

        private Long departmentId;

        private Long shopId;

        private Long positionId;



}
