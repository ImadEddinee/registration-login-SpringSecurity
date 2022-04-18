package com.ensas.userloginregistration.mapper;

import com.ensas.userloginregistration.dto.UserDto;
import com.ensas.userloginregistration.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);
}
