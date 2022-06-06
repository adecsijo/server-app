package org.thesis.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.thesis.dtos.ItemDetailsDto;
import org.thesis.entities.ShoppingList;
import org.thesis.repositories.ItemRepository;
import org.thesis.repositories.ShoppingListByUserRepository;
import org.thesis.repositories.ShoppingListRepository;
import org.thesis.repositories.UnitRepository;

import javax.inject.Inject;

@Mapper(componentModel = "cdi")
public abstract class ShoppingListMapper {

    @Inject
    ShoppingListByUserRepository shoppingListByUserRepository;

    @Inject
    ItemRepository itemRepository;

    @Inject
    UnitRepository unitRepository;

    @Mapping(target = "id", source = "id")
    @Mapping(target = "itemId", source = "item.id")
    @Mapping(target = "count", source = "itemCount")
    @Mapping(target = "unitId", source = "unit.id")
    public abstract ItemDetailsDto map(ShoppingList item);

    public ShoppingList map(Integer listId, ItemDetailsDto itemDetailsDto) {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setShoppingListByUser(shoppingListByUserRepository.findById(listId));
        shoppingList.setId(itemDetailsDto.getId());
        shoppingList.setItem(itemRepository.findById(itemDetailsDto.getItemId()));
        shoppingList.setItemCount(itemDetailsDto.getCount());
        shoppingList.setUnit(unitRepository.findById(itemDetailsDto.getUnitId()));
        return shoppingList;
    }
}
