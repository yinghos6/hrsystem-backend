package com.example.hr_system;

import com.example.hr_system.entity.LeaveBalance;
import com.example.hr_system.repository.LeaveBalanceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class LeaveRepoTest {

    @Autowired
    LeaveBalanceRepository leaveBalanceRepository;



}
