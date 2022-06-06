package org.thesis.services;

import org.thesis.dtos.ShoppingListByUserDto;
import org.thesis.entities.ShoppingListByUser;
import org.thesis.entities.Unit;
import org.thesis.exceptions.SimpleException;
import org.thesis.mappers.ShoppingListByUserMapper;
import org.thesis.repositories.ShoppingListByUserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ShoppingListByUserService {

    @Inject
    ShoppingListByUserRepository shoppingListByUserRepository;

    @Inject
    ShoppingListByUserMapper shoppingListByUserMapper;

    private static final String NOTHING_TO_SHOW = "There is no shopping list in database for this user!";
    private static final String ALREADY_EXISTS = "There is already a shopping list for this user with this name!";
    private static final String NO_SUCH_SHOPPING_LIST = "There is no shopping list for this user with this id!";

    public List<ShoppingListByUserDto> getAllShoppingListByUser(String username) throws SimpleException {
        List<ShoppingListByUser> all = shoppingListByUserRepository.findAllByUser(username);
        if (null == all || all.isEmpty()) {
            throw new SimpleException(NOTHING_TO_SHOW);
        }
        return all.stream().map(shoppingListByUserMapper::map).collect(Collectors.toList());
    }

    public void addNewShoppingListByUser(String username, ShoppingListByUserDto shoppingListByUserDto) throws SimpleException {
        if (shoppingListByUserRepository.existsByUserAndName(username, shoppingListByUserDto.getName())) {
            throw new SimpleException(ALREADY_EXISTS);
        }
        shoppingListByUserDto.setUsername(username);
        shoppingListByUserRepository.save(shoppingListByUserMapper.map(shoppingListByUserDto));
    }

    public void modifyShoppingListByUser(String username, ShoppingListByUserDto shoppingListByUserDto) throws SimpleException {
        ShoppingListByUser shoppingListByUser = shoppingListByUserRepository.findById(username, shoppingListByUserDto.getId());
        if (null == shoppingListByUser) {
            throw new SimpleException(NO_SUCH_SHOPPING_LIST);
        }
        if (shoppingListByUserRepository.existsByUserAndName(username, shoppingListByUserDto.getName())) {
            throw new SimpleException(ALREADY_EXISTS);
        }
        shoppingListByUser.setName(shoppingListByUserDto.getName());
        shoppingListByUserRepository.update(shoppingListByUser);
    }

    public void deleteShoppingListByUser(String username, ShoppingListByUserDto shoppingListByUserDto) throws SimpleException {
        ShoppingListByUser shoppingListByUser = shoppingListByUserRepository.findById(username, shoppingListByUserDto.getId());
        if (null == shoppingListByUser) {
            throw new SimpleException(NO_SUCH_SHOPPING_LIST);
        }
        shoppingListByUserRepository.delete(shoppingListByUser);
    }
}
