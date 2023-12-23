package com.example.hr_system.controller;


import com.example.hr_system.entity.Role;
import com.example.hr_system.entity.User;
import com.example.hr_system.payload.request.employee.UserInfoDto;
import com.example.hr_system.payload.response.UserInfoResponse;
import com.example.hr_system.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/user/")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/listAllUser")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable(value = "id")long id){
       User user = userService.findUserById(id);
       return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/userInfo")
    public ResponseEntity<UserInfoResponse>findUserInfoByUsername(@Valid @RequestBody UserInfoDto userInfoDto){
        User user = userService.findUserByUsername(userInfoDto.getUsername());
        List<Role> roleList = user.getRoles().stream().toList();
        return new ResponseEntity<UserInfoResponse>(new UserInfoResponse(user.getUsername(),user.getEmail(),roleList),HttpStatus.OK);
    }


}
