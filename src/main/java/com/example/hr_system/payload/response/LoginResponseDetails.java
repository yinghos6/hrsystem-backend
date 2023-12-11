package com.example.hr_system.payload.response;

import lombok.Data;

@Data
public class LoginResponseDetails {

    private String Status;
    private Long Code;

    private JwtResponse jwtResponse;

}
