package com.example.hr_system.controller;


import com.example.hr_system.entity.LeaveBalance;
import com.example.hr_system.entity.LeaveRecord;
import com.example.hr_system.entity.LeaveStatus;
import com.example.hr_system.entity.LeaveType;
import com.example.hr_system.payload.request.leave.EmployeeLeaveBalanceDTO;
import com.example.hr_system.payload.request.leave.EmployeeLeaveRecordDTO;
import com.example.hr_system.payload.response.MessageResponse;
import com.example.hr_system.repository.LeaveRecordRepository;
import com.example.hr_system.service.EmployeeService;
import com.example.hr_system.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/leave/")
public class LeaveController {


    @Autowired
    private LeaveService leaveService;

    @Autowired
    private EmployeeService employeeService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @GetMapping("/leaveTypeAll")
    public List<LeaveType> getAllLeaveType(){
        return leaveService.getAllLeaveType();
    }

    @GetMapping("/leaveStatusAll")
    public List<LeaveStatus> getAllLeaveStatus(){
        return leaveService.getAllLeaveStatus();
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

    @GetMapping("/{id}/leaveBalance")
    public ResponseEntity getEmployeeLeaveRecord(@PathVariable long id){
        Set<LeaveBalance> allRecord =  employeeService.getLeaveBalanceByEmployeeID(id);

        return new ResponseEntity<>(allRecord, HttpStatus.OK);
    }

    @PostMapping("/{id}/newRecord")
    public ResponseEntity<MessageResponse> createNewLeaveRecord(@PathVariable(value = "id")long id, @RequestBody EmployeeLeaveRecordDTO employeeLeaveRecordDTO){
        leaveService.createNewLeaveRecord(id, employeeLeaveRecordDTO);

        MessageResponse response = new MessageResponse();
        response.setCode(200L);
        response.setStatus("Created");
        response.setMessage("The leave record is created successfully.");

        return new ResponseEntity<MessageResponse>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/getAllRecord")
    public Page<LeaveRecord> getAllLeaveRecord(@PathVariable(value = "id")long id,
                                               @RequestParam(value = "page")Integer page,
                                               @RequestParam(value = "size")Integer size,
                                               @RequestParam(value = "sortField")String sortField,
                                               @RequestParam(value = "sortDir")String sortDir
                                              ){

        Pageable pageable = PageRequest.of(page  , size, Sort.by(sortDir.equals("asc")?Sort.Direction.ASC:Sort.Direction.DESC, sortField));

        Page<LeaveRecord> leaveRecords = leaveService.getAllLeaveRecordByEmployeeID(id, pageable);

        return leaveRecords;
    }



}
