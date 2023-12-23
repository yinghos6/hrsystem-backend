package com.example.hr_system.payload.response.employee;

import com.example.hr_system.entity.Department;
import com.example.hr_system.entity.Position;
import com.example.hr_system.entity.Shop;
import lombok.Data;

import java.util.List;

@Data
public class DropdownListResponse {

    List<Shop> shopList;

    List<Position> positionList;

    List<Department> departmentList;
}
