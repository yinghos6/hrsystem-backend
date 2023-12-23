package com.example.hr_system.payload.response.employee;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResultResponse {

    private Date createdTime;
    private String message;
    private String details;
}
