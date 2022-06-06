package org.thesis.services;

import org.thesis.dtos.ItemDetailsDto;
import org.thesis.entities.ShoppingList;
import org.thesis.exceptions.SimpleException;
import org.thesis.mappers.ShoppingListMapper;
import org.thesis.repositories.ShoppingListRepository;
import org.thesis.repositories.UnitRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ShoppingListService {

    @Inject
    ShoppingListRepository shoppingListRepository;

    @Inject
    ShoppingListMapper shoppingListMapper;

    @Inject
    UnitRepository unitRepository;

    private static final String NO_SUCH_LIST = "There is no such shopping list!";
    private static final String NO_SUCH_ITEM = "There is no such item in this shopping list!";

    public List<ItemDetailsDto> getShoppingListById(Integer listId) throws SimpleException {
        if (!shoppingListRepository.existsByField("shoppingListByUser.id", listId)) {
            throw new SimpleException(NO_SUCH_LIST);
        }
        return shoppingListRepository.findByField("shoppingListByUser.id", listId)
                .stream().map(shoppingListMapper::map).collect(Collectors.toList());
    }

    public void addItemToList(Integer listId, ItemDetailsDto itemDetailsDto) {
        if (shoppingListRepository.existsByListIdAndItemId(listId, itemDetailsDto.getItemId())) {
            ShoppingList shoppingListItem = shoppingListRepository.findByListIdAndItemId(listId, itemDetailsDto.getItemId());
            shoppingListItem.setItemCount(shoppingListItem.getItemCount() + itemDetailsDto.getCount());
            shoppingListRepository.update(shoppingListItem);
        } else {
            shoppingListRepository.save(shoppingListMapper.map(listId, itemDetailsDto));
        }
    }

    public void modifyListItem(Integer listId, ItemDetailsDto itemDetailsDto) throws SimpleException {
        if (!shoppingListRepository.existsByListIdAndItemId(listId, itemDetailsDto.getItemId())) {
            throw new SimpleException(NO_SUCH_ITEM);
        }
        ShoppingList shoppingListItem = shoppingListRepository.findByListIdAndItemId(listId, itemDetailsDto.getItemId());
        shoppingListItem.setItemCount(itemDetailsDto.getCount());
        shoppingListItem.setUnit(unitRepository.findById(itemDetailsDto.getUnitId()));
        shoppingListRepository.update(shoppingListItem);
    }

    public void deleteListItem(Integer listId, ItemDetailsDto itemDetailsDto) throws SimpleException {
        if (!shoppingListRepository.existsByListIdAndItemId(listId, itemDetailsDto.getItemId())) {
            throw new SimpleException(NO_SUCH_ITEM);
        }
        ShoppingList shoppingListItem = shoppingListRepository.findByListIdAndItemId(listId, itemDetailsDto.getItemId());
        shoppingListRepository.delete(shoppingListItem);
    }
}
