package com.example.hr_system.payload.response;

import lombok.Data;

@Data
public class MessageResponse {

    private Long code;
    private String status;

    private String message;
}
