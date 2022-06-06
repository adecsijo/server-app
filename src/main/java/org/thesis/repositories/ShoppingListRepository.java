package org.thesis.repositories;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class ShoppingListRepository {

    @Inject
    EntityManager em;
}
