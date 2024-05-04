package org.thesis.services;

import org.thesis.dtos.ShoppingListDto;
import org.thesis.entities.Item;
import org.thesis.entities.ShoppingList;
import org.thesis.exceptions.SimpleException;
import org.thesis.repositories.ItemRepository;
import org.thesis.repositories.ShoppingListRepository;
import org.thesis.repositories.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ShoppingListService {

    @Inject
    ShoppingListRepository shoppingListRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    ItemRepository itemRepository;

    private static final String ALREADY_EXISTS = "There is already a shopping list for this user with this name!";
    private static final String NO_SUCH_SHOPPING_LIST = "There is no shopping list for this user with this id!";

    public List<ShoppingListDto> getAllShoppingListByUser(String username) throws SimpleException {
        List<ShoppingList> all = shoppingListRepository.findAllByUser(username);
        return all.stream().map(this::map).collect(Collectors.toList());
    }

    public void addNewShoppingListByUser(String username, ShoppingListDto shoppingListDto) throws SimpleException {
        if (shoppingListRepository.existsByUserAndName(username, shoppingListDto.getName())) {
            throw new SimpleException(ALREADY_EXISTS);
        }
        shoppingListRepository.save(map(shoppingListDto, username));
    }

    public void modifyShoppingListByUser(String username, ShoppingListDto shoppingListDto) throws SimpleException {
        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListDto.getId());
        if (null == shoppingList) {
            throw new SimpleException(NO_SUCH_SHOPPING_LIST);
        }
        shoppingList.setName(shoppingListDto.getName());
        shoppingList.setDescription(shoppingListDto.getDescription());
        shoppingListRepository.update(shoppingList);
    }

    public void deleteShoppingListByUser(String username, ShoppingListDto shoppingListDto) throws SimpleException {
        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListDto.getId());
        if (null == shoppingList) {
            throw new SimpleException(NO_SUCH_SHOPPING_LIST);
        }
        List<Item> items = itemRepository.findByList(shoppingListDto.getId());
        items.forEach(item -> itemRepository.delete(item));
        shoppingListRepository.delete(shoppingList);
    }

    private ShoppingList map(ShoppingListDto shoppingListDto, String username) {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setId(shoppingListDto.getId());
        shoppingList.setName(shoppingListDto.getName());
        shoppingList.setUser(userRepository.findById(username));
        shoppingList.setDescription(shoppingListDto.getDescription());
        return shoppingList;
    }

    private ShoppingListDto map(ShoppingList shoppingList) {
        ShoppingListDto shoppingListDto = new ShoppingListDto();
        shoppingListDto.setId(shoppingList.getId());
        shoppingListDto.setName(shoppingList.getName());
        shoppingListDto.setDescription(shoppingList.getDescription());
        return shoppingListDto;
    }
}
