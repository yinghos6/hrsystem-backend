package com.example.hr_system.service.lmpl;

import com.example.hr_system.entity.*;
import com.example.hr_system.exception.ResourceNotFoundException;
import com.example.hr_system.payload.request.employee.EmployeeEdit;
import com.example.hr_system.payload.request.employee.EmployeeRegisterformDTO;
import com.example.hr_system.repository.EmployeeRepository;
import com.example.hr_system.service.DepartmentService;
import com.example.hr_system.service.EmployeeService;
import com.example.hr_system.service.PositionService;
import com.example.hr_system.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class EmployeeServicelmpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
     private PositionService positionService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private DepartmentService departmentService;


    public EmployeeServicelmpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }


//    @Override
//    public Page<Employee> getAllEmployee(Pageable pageable) {
//       return employeeRepository.findAll(pageable);
//    }

    @Override
    public Page<Employee> getAllEmployee(String keyword,Pageable pageable) {
        return employeeRepository.findAllByKeyword(keyword,pageable);
    }

    @Override
    public Page<Employee> getAllEmployeeByShop(Long shopID, Pageable pageable) {
        return employeeRepository.findAllByShop(shopID, pageable);
    }

    @Override
    public Employee findEmployeeById(long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee","id","id"));
         return employee;
    }

    @Override
    public Employee CreateEmployee(EmployeeRegisterformDTO employeeRegisterformDTO) {
        Employee newEmployee = new Employee();
        newEmployee.setStaff_number(employeeRegisterformDTO.getStaff_number());
        newEmployee.setChinese_FullName(employeeRegisterformDTO.getChi_fullName());
        newEmployee.setEnglish_Surname(employeeRegisterformDTO.getEng_surname());
        newEmployee.setEnglish_Given_Name(employeeRegisterformDTO.getEng_given_name());
        newEmployee.setEmployment_date(employeeRegisterformDTO.getEmployment_date());

        Shop shop = shopService.findShopById(employeeRegisterformDTO.getPositionId());
        newEmployee.setShop(shop);

        Position position = positionService.findPositionById(employeeRegisterformDTO.getPositionId());
        newEmployee.setPosition(position);

        Department department = departmentService.findDepartmentById(employeeRegisterformDTO.getDepartmentId());
        newEmployee.setDepartment(department);

        newEmployee.setActive_Status(Boolean.TRUE);

        employeeRepository.save(newEmployee);
        return newEmployee;
    }




    @Override
    public void DeleteEmployeeByID(Long id) {
        employeeRepository.deleteById(id);
   }

    @Override
    public void UpdateEmployeeStatusById(Long id) {
        employeeRepository.UpdateEmployeeStatus(id);
    }

    @Override
    public void editEmployeeProfile(EmployeeEdit employeeEdit) {

        Employee employee = employeeRepository.findById(employeeEdit.getEdited_employee_id()).orElseThrow(()->new RuntimeException("The user is not existed."));

        employee.setStaff_number(employeeEdit.getEdited_staff_number());
        employee.setChinese_FullName(employeeEdit.getEdited_chi_fullName());
        employee.setEnglish_Surname(employeeEdit.getEdited_eng_surname());
        employee.setEnglish_Given_Name(employeeEdit.getEdited_given_name());
        employee.setEmployment_date(employeeEdit.getEdited_employment_date());

        Shop shop = shopService.findShopById(employeeEdit.getEdited_shopId());
        employee.setShop(shop);

        Position position = positionService.findPositionById(employeeEdit.getEdited_positionId());
        employee.setPosition(position);

        Department department = departmentService.findDepartmentById(employeeEdit.getEdited_departmentId());
        employee.setDepartment(department);

        employeeRepository.save(employee);
    }

    @Override
    public Set<LeaveBalance> getLeaveBalanceByEmployeeID(Long employeeID) {

        Employee employee = employeeRepository.findById(employeeID).orElseThrow(()->new RuntimeException("The user is not existed."));



        Set<LeaveBalance> employeeLeaveBalanceSet = employee.getLeaveBalances();

        return employeeLeaveBalanceSet;
    }

    @Override
    public Long countTotalEmployeeByActiveStatus(Long activeStatusID) {
        return employeeRepository.countEmployeeByActiveStatus(activeStatusID);
    }


}
