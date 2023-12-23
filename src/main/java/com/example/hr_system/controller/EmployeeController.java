package com.example.hr_system.controller;

import com.example.hr_system.entity.Employee;
import com.example.hr_system.payload.request.employee.EmployeeEdit;
import com.example.hr_system.payload.request.employee.EmployeeRegisterformDTO;
import com.example.hr_system.payload.response.*;
import com.example.hr_system.payload.response.employee.DeleteEmployeeResponse;
import com.example.hr_system.payload.response.employee.DropdownListResponse;
import com.example.hr_system.payload.response.employee.EmployeeRegisterformResponse;
import com.example.hr_system.payload.response.employee.UpdateEmployeeResponse;
import com.example.hr_system.service.DepartmentService;
import com.example.hr_system.service.EmployeeService;
import com.example.hr_system.service.PositionService;
import com.example.hr_system.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/employee/")
public class EmployeeController {

    @Autowired
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
    public Page<Employee> getAllEmployee(@RequestParam(value = "page")Integer page,
                                         @RequestParam(value = "size")Integer size,
                                         @RequestParam(value = "sortField")String sortField,
                                         @RequestParam(value = "sortDir")String sortDir,
                                         @RequestParam(value = "keyword")String keyword
    ){


            Pageable pageable = PageRequest.of(page  , size, Sort.by(sortDir.equals("asc")?Sort.Direction.ASC:Sort.Direction.DESC, sortField));
            Page<Employee> pagedResult = employeeService.getAllEmployee(keyword,pageable);


        return pagedResult;
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


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DeleteEmployeeResponse> deleteEmployeeById(@PathVariable(value = "id")long id){
        employeeService.DeleteEmployeeByID(id);
        DeleteEmployeeResponse response = new DeleteEmployeeResponse();
        response.setCode(200L);
        response.setStatus("Deleted");
        response.setMessage("The employee had been deleted successfully.");
        return new ResponseEntity<DeleteEmployeeResponse>(response, HttpStatus.OK);
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<UpdateEmployeeResponse> updateEmployeeStatusById(@PathVariable(value = "id")long id){
        employeeService.UpdateEmployeeStatusById(id);
        UpdateEmployeeResponse response = new UpdateEmployeeResponse();
        response.setCode(200L);
        response.setStatus("Updated");
        response.setMessage("The employee had been updated successfully.");

        return new ResponseEntity<UpdateEmployeeResponse>(response, HttpStatus.OK);
    }


    @PutMapping("/editProfile")
    public ResponseEntity<MessageResponse> EditEmployeeProfileById(@RequestBody EmployeeEdit employeeEdit){
        employeeService.editEmployeeProfile(employeeEdit);
        MessageResponse response = new MessageResponse();
        response.setCode(200L);
        response.setStatus("Edited");
        response.setMessage("The employee had been edited successfully.");
        return new ResponseEntity<MessageResponse>(response, HttpStatus.OK);
    }

}
