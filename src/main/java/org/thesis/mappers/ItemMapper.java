package org.thesis.mappers;

import org.mapstruct.Mapper;
import org.thesis.dtos.ItemDto;
import org.thesis.entities.Item;

@Mapper(componentModel = "cdi")
public interface ItemMapper {

    Item map(ItemDto itemDto);

    ItemDto map(Item item);
}
