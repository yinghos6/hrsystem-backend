package com.example.hr_system.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class UserRegisterDto {

    @NotNull
    @Size(min = 4, message = "The username should have at least 4 characters.")
    private String username;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 4, message = "The password should have at least 4 characters.")
    private String password;


}
