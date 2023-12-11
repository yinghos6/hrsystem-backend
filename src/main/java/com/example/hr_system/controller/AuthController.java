package com.example.hr_system.controller;

import com.example.hr_system.payload.request.UserRegisterDto;
import com.example.hr_system.payload.request.UserLoginRequest;
import com.example.hr_system.payload.response.JwtResponse;
import com.example.hr_system.payload.response.LoginResponseDetails;
import com.example.hr_system.payload.response.RegisterResultResponse;
import com.example.hr_system.repository.RoleRespository;
import com.example.hr_system.repository.UserRepository;
import com.example.hr_system.security.JwtTokenProvider;
import com.example.hr_system.service.UserService;
import com.example.hr_system.service.auth.UserDetailslmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRespository roleRespository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResultResponse> registerNewUser(@RequestBody UserRegisterDto userRegisterDto){
        RegisterResultResponse response = userService.registerNewUser(userRegisterDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @PostMapping("/signin")
    public ResponseEntity<LoginResponseDetails> authenicateUser(@RequestBody UserLoginRequest userLoginRequest){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginRequest.getUsername(),userLoginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenProvider.generationToken(authentication);

            UserDetailslmpl userDetails = (UserDetailslmpl) authentication.getPrincipal();

            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item->item.getAuthority())
                    .collect(Collectors.toList());
            JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),userDetails.getEmail(),roles);
            LoginResponseDetails LoginInfo = new LoginResponseDetails();
            LoginInfo.setCode(200L);
            LoginInfo.setStatus("success");
            LoginInfo.setJwtResponse(jwtResponse);




        return new ResponseEntity<LoginResponseDetails>(LoginInfo,HttpStatus.OK);

    }

}
