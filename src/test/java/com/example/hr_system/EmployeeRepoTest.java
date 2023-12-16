package com.example.hr_system;

import com.example.hr_system.entity.Employee;
import com.example.hr_system.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class EmployeeRepoTest {

    @Autowired
    EmployeeRepository employeeRepository;



    @Test
    public void testSearchEmployee(){
        String keyword = "demo";

        int pageNumber = 0;
        int pageSize = 4;

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Employee> page = employeeRepository.findAllByKeyword(keyword, pageable);
        List<Employee> listEmployee = page.getContent();
        System.out.println(listEmployee);
    }
}
