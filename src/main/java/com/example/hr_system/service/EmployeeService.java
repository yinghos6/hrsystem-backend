package com.example.hr_system.service;

import com.example.hr_system.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployee();

    Employee findEmployeeById(long id);

    Employee CreateEmployee(Employee employee);




}
