package com.example.hr_system.payload;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class UserRegisterDto {


    private String username;

    private String email;

    private String password;


}
