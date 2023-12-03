package com.example.hr_system.service.lmpl;

import com.example.hr_system.entity.Employee;
import com.example.hr_system.exception.ResourceNotFoundException;
import com.example.hr_system.repository.EmployeeRepository;
import com.example.hr_system.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServicelmpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeServicelmpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee","id","id"));
        return employee;
    }

    @Override
    public Employee CreateEmployee(Employee employee) {
        Employee employeeResponse = employeeRepository.save(employee);
        return employeeResponse;
    }
}
