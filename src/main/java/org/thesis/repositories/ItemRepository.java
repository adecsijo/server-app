package org.thesis.repositories;

import org.thesis.entities.Item;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ItemRepository extends Repository<Item, Integer> {

    protected ItemRepository() {
        super(Item.class);
    }

    public List<Item> findByList(Integer listId) {
        return em.createQuery("select e from Item e where e.shoppingListId.id =? 1", Item.class)
                .setParameter(1, listId).getResultList();
    }
}
