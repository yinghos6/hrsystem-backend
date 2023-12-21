package com.example.hr_system.payload.response;

import lombok.Data;

@Data
public class UpdateEmployeeResponse {

    private Long code;
    private String status;

    private String message;
}
