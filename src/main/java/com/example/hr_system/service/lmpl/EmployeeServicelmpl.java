package com.example.hr_system.service.lmpl;

import com.example.hr_system.entity.Department;
import com.example.hr_system.entity.Employee;
import com.example.hr_system.entity.Position;
import com.example.hr_system.entity.Shop;
import com.example.hr_system.exception.ResourceNotFoundException;
import com.example.hr_system.payload.request.EmployeeRegisterformDTO;
import com.example.hr_system.repository.EmployeeRepository;
import com.example.hr_system.service.DepartmentService;
import com.example.hr_system.service.EmployeeService;
import com.example.hr_system.service.PositionService;
import com.example.hr_system.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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


    @Override
    public Page<Employee> getAllEmployee(Pageable pageable) {
       return employeeRepository.findAll(pageable);
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
        System.out.println(shop);

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



}
