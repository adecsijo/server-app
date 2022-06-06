package org.thesis.services;

import org.thesis.dtos.UnitDto;
import org.thesis.entities.Unit;
import org.thesis.exceptions.SimpleException;
import org.thesis.mappers.UnitMapper;
import org.thesis.repositories.UnitRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class UnitService {

    private static final String ALREADY_EXISTS = "This unit is already exists!";
    private static final String NOTHING_TO_SHOW = "There is no unit in database!";
    @Inject
    UnitRepository unitRepository;

    @Inject
    UnitMapper unitMapper;

    public List<Unit> getAllUnit() throws SimpleException {
        List<Unit> all = unitRepository.findAll();
        if (null == all || all.isEmpty()) {
            throw new SimpleException(NOTHING_TO_SHOW);
        }
        return all;
    }

    public void addNewUnit(UnitDto unitDto) throws SimpleException {
        if (unitRepository.existsByField("name", unitDto.getName())) {
            throw new SimpleException(ALREADY_EXISTS);
        }
        unitRepository.save(unitMapper.map(unitDto));
    }
}
