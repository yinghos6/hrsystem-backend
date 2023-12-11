package com.example.hr_system;

import com.example.hr_system.security.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootTest(classes = HrSystemApplication.class)
public class JWTtesting {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Test
    public void JWTtesting(){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken("Mapper","Mapper"));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generationToken(authentication);

        System.out.println(jwt);
    }

    @Test
    public void JWTfreshToken(){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken("Mapper","Mapper"));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generationToken(authentication);

        System.out.println(jwt);

        String updatedToken = jwtTokenProvider.refreshToken(jwt);
        System.out.println(updatedToken);
    }

}
