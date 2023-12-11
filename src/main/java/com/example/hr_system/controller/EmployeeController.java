package com.example.hr_system.controller;

import com.example.hr_system.entity.Employee;
import com.example.hr_system.payload.request.EmployeeRegisterformDTO;
import com.example.hr_system.payload.response.DropdownListResponse;
import com.example.hr_system.payload.response.EmployeeRegisterformResponse;
import com.example.hr_system.service.DepartmentService;
import com.example.hr_system.service.EmployeeService;
import com.example.hr_system.service.PositionService;
import com.example.hr_system.service.ShopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/employee/")
public class EmployeeController {

    private EmployeeService employeeService;
    private ShopService shopService;

    private DepartmentService departmentService;

    private PositionService positionService;

    public EmployeeController(EmployeeService employeeService, ShopService shopService, DepartmentService departmentService, PositionService positionService) {
        this.employeeService = employeeService;
        this.shopService = shopService;
        this.departmentService = departmentService;
        this.positionService = positionService;
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


    @GetMapping("/info")
    public ResponseEntity<DropdownListResponse> getDropdownList(){

        DropdownListResponse dropdownListResponse = new DropdownListResponse();

        dropdownListResponse.setShopList(shopService.getAllShop());
        dropdownListResponse.setPositionList(positionService.getAllPosition());
        dropdownListResponse.setDepartmentList(departmentService.getAllDepartment());

        return new ResponseEntity<DropdownListResponse>(dropdownListResponse,HttpStatus.OK);

    }

    @PostMapping("/create")
    public ResponseEntity<EmployeeRegisterformResponse> createNewEmployee(@RequestBody EmployeeRegisterformDTO employeeRegisterformDTO){

        Employee newEmployee = employeeService.CreateEmployee(employeeRegisterformDTO);

        EmployeeRegisterformResponse employeeResponse = new EmployeeRegisterformResponse();

        employeeResponse.setCode("200");
        employeeResponse.setStatus("success, The created time is " + newEmployee.getCreatedTime());
        employeeResponse.setMessage("The user: "+ newEmployee.getEnglish_Surname() +" "+newEmployee.getEnglish_Given_Name() +" is created successfully." );


        return new ResponseEntity<EmployeeRegisterformResponse>(employeeResponse, HttpStatus.CREATED);
    }

}
