package org.thesis.mappers;

import org.mapstruct.Mapper;
import org.thesis.dtos.UnitDto;
import org.thesis.entities.Unit;

@Mapper(componentModel = "cdi")
public interface UnitMapper {

    Unit map(UnitDto unitDto);

    UnitDto map(Unit unit);
}
