package com.example.hr_system.service;

import com.example.hr_system.entity.Employee;
import com.example.hr_system.payload.request.EmployeeRegisterformDTO;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployee();

    Employee findEmployeeById(long id);

    Employee CreateEmployee(EmployeeRegisterformDTO employeeRegisterformDTO);




}
