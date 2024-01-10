package com.example.hr_system.controller;

import com.example.hr_system.entity.Employee;
import com.example.hr_system.entity.PayrollRecord;
import com.example.hr_system.payload.request.payroll.PayrollRecordDTO;
import com.example.hr_system.payload.response.MessageResponse;
import com.example.hr_system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/payroll/")
public class PayrollController {


    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private LeaveService leaveService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private PayrollService payrollService;

    public PayrollController() {
    }

    public PayrollController(EmployeeService employeeService, LeaveService leaveService, ShopService shopService, DepartmentService departmentService, PositionService positionService, PayrollService payrollService) {
        this.employeeService = employeeService;
        this.leaveService = leaveService;
        this.shopService = shopService;
        this.departmentService = departmentService;
        this.positionService = positionService;
        this.payrollService = payrollService;
    }

    @PostMapping("/{id}/create")
    public ResponseEntity<MessageResponse> createNewPayrollRecord(@PathVariable(value = "id")long id, @RequestBody PayrollRecordDTO payrollRecordDTO){
        payrollService.createNewPayrollRecord(id, payrollRecordDTO);

        MessageResponse response = new MessageResponse();
        response.setCode(200L);
        response.setStatus("Created");
        response.setMessage("The payroll record is created successfully.");

        return new ResponseEntity<MessageResponse>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/allPayrollRecord")
    public List<PayrollRecord> getAllEmployeePayrollRecord(@PathVariable(value = "id")long id){
       return payrollService.findAllPayrollRecordByEmployeeID(id);
    }
}
