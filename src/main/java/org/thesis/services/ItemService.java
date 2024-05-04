package org.thesis.services;

import org.thesis.dtos.ItemDto;
import org.thesis.entities.Item;
import org.thesis.exceptions.SimpleException;
import org.thesis.repositories.ItemRepository;
import org.thesis.repositories.ShoppingListRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ItemService {

    private static final String NOTHING_TO_SHOW = "There is no item in database!";
    private static final String ALREADY_EXISTS = "This unit is already exists!";
    private static final String NO_SUCH_ITEM = "There is no such item!";

    @Inject
    ItemRepository itemRepository;

    @Inject
    ShoppingListRepository shoppingListRepository;

    public List<ItemDto> getAllItemByListId(Integer listId) throws SimpleException {
        List<Item> all = itemRepository.findByList(listId);
        return all.stream().map(this::map).collect(Collectors.toList());
    }

    public void addNewItem(Integer listId, ItemDto itemDto) throws SimpleException {
        if (itemRepository.existsByField("name", itemDto.getName())) {
            throw new SimpleException(ALREADY_EXISTS);
        }
        itemRepository.save(map(itemDto, listId));
    }

    public void deleteItem(ItemDto itemDto) throws SimpleException {
        Item item = itemRepository.findById(itemDto.getId());
        if (null == item) {
            throw new SimpleException(NO_SUCH_ITEM);
        }
        itemRepository.delete(item);
    }

    private Item map (ItemDto itemDto, Integer listId) {
        Item item = new Item();
        item.setId(itemDto.getId());
        item.setName(itemDto.getName());
        item.setShoppingListId(shoppingListRepository.findById(listId));
        return item;
    }

    private ItemDto map (Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setName(item.getName());
        return itemDto;
    }
}
