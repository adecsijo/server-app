package org.thesis.repositories;

import org.thesis.entities.Unit;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class UnitRepository {

    @Inject
    EntityManager em;

    @Transactional
    public List<Unit> getAllUnit() {
        return em.createQuery("select e from Unit e").getResultList();
    }
}
