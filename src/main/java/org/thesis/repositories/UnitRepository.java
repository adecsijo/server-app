package org.thesis.repositories;

import org.thesis.entities.Unit;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UnitRepository extends Repository<Unit, Integer>{

    protected UnitRepository() {
        super(Unit.class);
    }
}
