package org.thesis.repositories;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class ItemRepository {

    @Inject
    EntityManager em;
}
