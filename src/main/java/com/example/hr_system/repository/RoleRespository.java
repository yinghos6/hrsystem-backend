package com.example.hr_system.repository;

import com.example.hr_system.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface RoleRespository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
