package org.thesis.repositories;

import org.thesis.entities.ShoppingListByUser;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ShoppingListByUserRepository extends Repository<ShoppingListByUser, Integer> {

    protected ShoppingListByUserRepository() {
        super(ShoppingListByUser.class);
    }
}
