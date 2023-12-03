package com.example.hr_system.controller;

import com.example.hr_system.payload.LoginRequest;
import com.example.hr_system.payload.UserRegisterDto;
import com.example.hr_system.payload.response.RegisterResultResponse;
import com.example.hr_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class AuthController {


    @Autowired
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResultResponse> registerNewUser(@RequestBody UserRegisterDto userRegisterDto){
        RegisterResultResponse response = userService.registerNewUser(userRegisterDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenicateUser(@RequestBody LoginRequest loginRequest){
        return null;
    }

}
