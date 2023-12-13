package com.example.hr_system.service;

import com.example.hr_system.entity.Employee;
import com.example.hr_system.payload.request.EmployeeRegisterformDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    Page<Employee> getAllEmployee(Pageable pageable);

    Employee findEmployeeById(long id);

    Employee CreateEmployee(EmployeeRegisterformDTO employeeRegisterformDTO);

    void DeleteEmployeeByID(Long id);

}
