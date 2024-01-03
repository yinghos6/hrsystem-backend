package com.example.hr_system.payload.response.employee;

import lombok.Data;

@Data
public class GetTotalEmployeeResponse {

    private Long code;
    private String status;

    private Long totalNumber;
}
