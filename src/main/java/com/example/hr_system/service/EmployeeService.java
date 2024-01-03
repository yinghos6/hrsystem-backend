package com.example.hr_system.service;

import com.example.hr_system.entity.Employee;
import com.example.hr_system.entity.LeaveBalance;
import com.example.hr_system.payload.request.employee.EmployeeEdit;
import com.example.hr_system.payload.request.employee.EmployeeRegisterformDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface EmployeeService {

    Page<Employee> getAllEmployee(String keyword,Pageable pageable);

    Page<Employee> getAllEmployeeByShop(Long shopID, Pageable pageable);

    Employee findEmployeeById(long id);

    Employee CreateEmployee(EmployeeRegisterformDTO employeeRegisterformDTO);

    void DeleteEmployeeByID(Long id);

    void UpdateEmployeeStatusById(Long id);

    void editEmployeeProfile(EmployeeEdit employeeEdit);

    Set<LeaveBalance> getLeaveBalanceByEmployeeID(Long employeeID);

    Long countTotalEmployeeByActiveStatus(Long activeStatusID);

}
