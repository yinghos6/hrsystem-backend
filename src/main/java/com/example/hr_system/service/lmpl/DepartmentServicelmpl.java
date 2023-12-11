package com.example.hr_system.service.lmpl;

import com.example.hr_system.entity.Department;
import com.example.hr_system.repository.DepartmentRepository;
import com.example.hr_system.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServicelmpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findDepartmentById(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(()->new RuntimeException("the department is not found"));
        return department;
    }


}
