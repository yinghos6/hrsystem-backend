package com.example.hr_system.payload.response;

import com.example.hr_system.entity.Role;
import lombok.*;

import java.util.List;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponse {

    private String username;

    private String email;

    private List<Role> roles;
}
