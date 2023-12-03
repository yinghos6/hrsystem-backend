package com.example.hr_system;


import com.example.hr_system.entity.Employee;
import com.example.hr_system.entity.User;
import com.example.hr_system.repository.UserRepository;
import com.example.hr_system.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;


@SpringBootTest(classes = HrSystemApplication.class)
@Rollback(value = false)
public class UseRepoTest {

    @Autowired
    private UserService userService;

    @Test
    public void testUserPassword(){

        String pass = "visit";

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String userPassword = userService.findUserById(6).getPassword();
        System.out.println(userPassword);
        System.out.println(pass);
        String newPassword = encoder.encode(pass);
        System.out.println(newPassword);



        boolean matches = encoder.matches(pass, userPassword);

        System.out.println(matches);
    }



}
