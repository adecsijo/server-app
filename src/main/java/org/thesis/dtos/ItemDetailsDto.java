package org.thesis.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ItemDetailsDto {

    private Integer id;
    private Integer itemId;
    private Double count;
    private Integer unitId;
}
