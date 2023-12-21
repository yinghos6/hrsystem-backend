package com.example.hr_system.service;

import com.example.hr_system.entity.Employee;
import com.example.hr_system.payload.request.EmployeeEdit;
import com.example.hr_system.payload.request.EmployeeRegisterformDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

    Page<Employee> getAllEmployee(String keyword,Pageable pageable);

    Employee findEmployeeById(long id);

    Employee CreateEmployee(EmployeeRegisterformDTO employeeRegisterformDTO);

    void DeleteEmployeeByID(Long id);

    void UpdateEmployeeStatusById(Long id);

    void editEmployeeProfile(EmployeeEdit employeeEdit);


}
