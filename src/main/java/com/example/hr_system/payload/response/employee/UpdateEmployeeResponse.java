package com.example.hr_system.payload.response.employee;

import lombok.Data;

@Data
public class UpdateEmployeeResponse {

    private Long code;
    private String status;

    private String message;
}
