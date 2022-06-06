package org.thesis.services;

import org.thesis.entities.Unit;
import org.thesis.repositories.UnitRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class UnitService {

    @Inject
    UnitRepository unitRepository;

    public List<Unit> getAllUnit() {
        return unitRepository.getAllUnit();
    }
}
