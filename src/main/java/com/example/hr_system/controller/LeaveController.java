package com.example.hr_system.controller;


import com.example.hr_system.entity.LeaveType;
import com.example.hr_system.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/leave/")
public class LeaveController {


    @Autowired
    private LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @GetMapping("/leaveTypeAll")
    public List<LeaveType> getAllLeaveType(){
        return leaveService.getAllLeaveType();
    }
}
