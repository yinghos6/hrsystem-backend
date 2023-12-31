package com.example.hr_system.payload.response;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

    private String jwt_token;

    private long UserId;

    private String username;

    private String email;

    private List<String> roles;
}
