package com.example.hr_system.controller;

import com.example.hr_system.entity.Employee;
import com.example.hr_system.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/employee/")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/getAllEmployee")
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable(value = "id")long id){
        Employee employee = employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Employee> createNewEmployee(@RequestBody Employee employee){
        Employee employeeResponse = employeeService.CreateEmployee(employee);
        return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
    }
}
