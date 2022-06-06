package org.thesis.repositories;

import org.thesis.entities.ShoppingList;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ShoppingListRepository extends Repository<ShoppingList, Integer> {

    protected ShoppingListRepository() {
        super(ShoppingList.class);
    }
}
