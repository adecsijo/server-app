package org.thesis.repositories;

import org.thesis.entities.Item;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ItemRepository extends Repository<Item, Integer> {

    protected ItemRepository() {
        super(Item.class);
    }
}
