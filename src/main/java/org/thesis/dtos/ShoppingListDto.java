package org.thesis.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor
public class ShoppingListDto {
    private Integer listId;
    private List<ItemDetailsDto> items;

}
