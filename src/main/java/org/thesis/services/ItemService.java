package org.thesis.services;

import org.thesis.dtos.ItemDto;
import org.thesis.entities.Item;
import org.thesis.exceptions.SimpleException;
import org.thesis.mappers.ItemMapper;
import org.thesis.repositories.ItemRepository;

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
    ItemMapper itemMapper;

    public List<ItemDto> getAllItem() throws SimpleException {
        List<Item> all = itemRepository.findAll();
        if (null == all || all.isEmpty()) {
            throw new SimpleException(NOTHING_TO_SHOW);
        }
        return all.stream().map(itemMapper::map).collect(Collectors.toList());
    }

    public void addNewItem(ItemDto itemDto) throws SimpleException {
        if (itemRepository.existsByField("name", itemDto.getName())) {
            throw new SimpleException(ALREADY_EXISTS);
        }
        itemRepository.save(itemMapper.map(itemDto));
    }

    public void modifyItem(ItemDto itemDto) throws SimpleException {
        Item item = itemRepository.findById(itemDto.getId());
        if (null == item) {
            throw new SimpleException(NO_SUCH_ITEM);
        }
        item.setName(itemDto.getName());
        itemRepository.update(item);
    }

    public void deleteItem(ItemDto itemDto) throws SimpleException {
        Item item = itemRepository.findById(itemDto.getId());
        if (null == item) {
            throw new SimpleException(NO_SUCH_ITEM);
        }
        itemRepository.delete(item);
    }
}
