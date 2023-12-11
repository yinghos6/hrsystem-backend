package com.example.hr_system.mapper;

import com.example.hr_system.entity.User;
import com.example.hr_system.payload.request.UserRegisterDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserRegistrationMapper {

    UserRegistrationMapper INSTANCE = Mappers.getMapper(UserRegistrationMapper.class);

    @Mappings({
            @Mapping(target = "username", source = "username"),
            @Mapping(target = "password", source = "password"),
            @Mapping(target = "email", source = "email")
        })
    User toUserEntity(UserRegisterDto userRegisterDto);



    @Mappings({
            @Mapping(target = "username", source = "username"),
            @Mapping(target = "password", source = "password"),
            @Mapping(target = "email", source = "email")
    })
    UserRegisterDto toUserRegistrationDto(User user);

}
