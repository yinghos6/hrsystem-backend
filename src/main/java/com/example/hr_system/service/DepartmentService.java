package com.example.hr_system.service;

import com.example.hr_system.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    List<Department> getAllDepartment();

    Department findDepartmentById(Long id);
}
