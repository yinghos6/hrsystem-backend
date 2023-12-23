package com.example.hr_system.controller;


import com.example.hr_system.entity.LeaveType;
import com.example.hr_system.payload.request.leave.EmployeeLeaveBalanceDTO;
import com.example.hr_system.payload.response.MessageResponse;
import com.example.hr_system.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/newLeaveBalacne/{id}")
    public ResponseEntity<MessageResponse> craeteNewEmployeeLeaveBalance(@PathVariable(value = "id")long id, @RequestBody EmployeeLeaveBalanceDTO employeeLeaveBalanceDTO){
        leaveService.createNewLeaveBalance(id, employeeLeaveBalanceDTO);

        MessageResponse response = new MessageResponse();
        response.setCode(200L);
        response.setStatus("updated");
        response.setMessage("The leave balance is updated for the employee.");

        return new ResponseEntity<MessageResponse>(response, HttpStatus.OK);
    }


}
