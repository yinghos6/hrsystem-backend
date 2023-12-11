package com.example.hr_system.service.lmpl;

import com.example.hr_system.entity.Role;
import com.example.hr_system.entity.User;
import com.example.hr_system.exception.ResourceNotFoundException;
import com.example.hr_system.mapper.UserRegistrationMapper;
import com.example.hr_system.payload.request.UserRegisterDto;
import com.example.hr_system.payload.request.UserLoginRequest;
import com.example.hr_system.payload.response.RegisterResultResponse;
import com.example.hr_system.repository.RoleRespository;
import com.example.hr_system.repository.UserRepository;
import com.example.hr_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServicelmpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRespository roleRespository;


    public UserServicelmpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }




    @Override
    public RegisterResultResponse registerNewUser(UserRegisterDto userRegisterDto) {

        User userInput = UserRegistrationMapper.INSTANCE.toUserEntity(userRegisterDto);


        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(userInput.getPassword());
        User user = new User();

        user.setEmail(userInput.getEmail());
        user.setUsername(userInput.getUsername());
        user.setPassword(encodedPassword);

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRespository.findByName("ROLE_ADMIN").get();
        roles.add(userRole);

        user.setRoles(roles);

        String userName = user.getUsername();

        userRepository.save(user);
        return new RegisterResultResponse(new Date(), "The user is created successfully", userName);
    }

    @Override
    public User findUserById(long id) {
        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id", "id"));
        return user;
    }

    @Override
    public User findUserByUsername(String username) {
       User user = userRepository.findByUsername(username);
        return user;
    }

    @Override
    public Boolean registerExistByUsername(String username) {
        return null;
    }

    @Override
    public Boolean registerExistByEmail(String email) {
        return null;
    }

    @Override
    public String login(UserLoginRequest userLoginRequest) {
        return null;
    }


}
