package com.example.hr_system.service;

import com.example.hr_system.entity.User;
import com.example.hr_system.payload.UserRegisterDto;
import com.example.hr_system.payload.response.RegisterResultResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUser();

    RegisterResultResponse registerNewUser(UserRegisterDto userRegisterDto);

    User findUserById(long id);

    Boolean registerExistByUsername(String username);

    Boolean registerExistByEmail(String email);

}
