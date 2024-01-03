package com.example.hr_system.repository;

import com.example.hr_system.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(nativeQuery = true, value = "SELECT * from  employee e where e.en_surname or e.en_given_name like %?1%")
    public Page<Employee> findAllByKeyword(String keyword, Pageable pageable);

    @Query(nativeQuery = true, value = "UPDATE employee e set e.active_status = if(e.active_status,0,1)  where e.id = ?1")
    public void UpdateEmployeeStatus(Long employeeId);

    @Query(nativeQuery = true, value = "SELECT * from employee e where e.shop_id = ?1")
    public Page<Employee> findAllByShop(Long shopID, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT  COUNT(*) from employee e where e.active_status = ?1")
    public Long countEmployeeByActiveStatus(Long activeStatusID);
}
