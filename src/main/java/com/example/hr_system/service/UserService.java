package com.example.hr_system.service;

import com.example.hr_system.entity.User;
import com.example.hr_system.payload.request.UserRegisterDto;
import com.example.hr_system.payload.request.UserLoginRequest;
import com.example.hr_system.payload.response.RegisterResultResponse;

import java.util.List;

public interface UserService {

    List<User> getAllUser();

    RegisterResultResponse registerNewUser(UserRegisterDto userRegisterDto);

    User findUserById(long id);

    User findUserByUsername(String username);

    Boolean registerExistByUsername(String username);

    Boolean registerExistByEmail(String email);

    String login(UserLoginRequest userLoginRequest);
}
