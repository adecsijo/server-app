package org.thesis.mappers;

import org.mapstruct.Mapper;
import org.thesis.dtos.UserDto;
import org.thesis.entities.User;

@Mapper(componentModel = "cdi")
public interface UserMapper {

    UserDto map(User user);

    User map(UserDto userDto);
}
