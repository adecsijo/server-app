package org.thesis.services;

import org.thesis.dtos.UnitDto;
import org.thesis.entities.Unit;
import org.thesis.exceptions.SimpleException;
import org.thesis.mappers.UnitMapper;
import org.thesis.repositories.UnitRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UnitService {

    private static final String ALREADY_EXISTS = "This unit is already exists!";
    private static final String NOTHING_TO_SHOW = "There is no unit in database!";
    private static final String NO_SUCH_UNIT = "There is no such unit!";
    @Inject
    UnitRepository unitRepository;

    @Inject
    UnitMapper unitMapper;

    public List<UnitDto> getAllUnit() throws SimpleException {
        List<Unit> all = unitRepository.findAll();
        if (null == all || all.isEmpty()) {
            throw new SimpleException(NOTHING_TO_SHOW);
        }
        return all.stream().map(unitMapper::map).collect(Collectors.toList());
    }

    public void addNewUnit(UnitDto unitDto) throws SimpleException {
        if (unitRepository.existsByField("name", unitDto.getName())) {
            throw new SimpleException(ALREADY_EXISTS);
        }
        unitRepository.save(unitMapper.map(unitDto));
    }

    public void modifyUnit(UnitDto unitDto) throws SimpleException {
        Unit unit = unitRepository.findById(unitDto.getId());
        if (null == unit) {
            throw new SimpleException(NO_SUCH_UNIT);
        }
        unit.setName(unitDto.getName());
        unitRepository.update(unit);
    }

    public void deleteUnit(UnitDto unitDto) throws SimpleException {
        Unit unit = unitRepository.findById(unitDto.getId());
        if (null == unit) {
            throw new SimpleException(NO_SUCH_UNIT);
        }
        unitRepository.delete(unit);
    }
}
