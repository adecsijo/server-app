package org.thesis.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter @NoArgsConstructor
public class ShoppingListByUserDto implements Serializable {

    private Integer id;
    private String username;
    private String name;
}
