package org.thesis.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.thesis.dtos.ShoppingListByUserDto;
import org.thesis.entities.ShoppingListByUser;
import org.thesis.entities.User;
import org.thesis.repositories.UserRepository;

import javax.inject.Inject;

@Mapper(componentModel = "cdi")
public abstract class ShoppingListByUserMapper {

    @Inject
    UserRepository userRepository;

    @Mapping(target = "username", source = "user.username")
    public abstract ShoppingListByUserDto map(ShoppingListByUser shoppingListByUser);

    @Mapping(target = "user.username", source = "username", qualifiedByName = "getUser")
    public abstract ShoppingListByUser map(ShoppingListByUserDto shoppingListByUserDto);

    @Named("getUser")
    private User getUser(String username) {
        return userRepository.findById(username);
    }
}
