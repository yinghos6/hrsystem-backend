package com.example.hr_system.repository;

import com.example.hr_system.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   boolean findByUsernameOrEmail(String username, String email);

   User findByUsername(String username);

   Boolean existsByUsername(String username);

   Boolean existsByEmail(String email);
}
