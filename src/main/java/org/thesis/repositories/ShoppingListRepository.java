package org.thesis.repositories;

import org.thesis.entities.ShoppingList;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class ShoppingListRepository extends Repository<ShoppingList, Integer> {

    @Inject
    EntityManager em;

    protected ShoppingListRepository() {
        super(ShoppingList.class);
    }

    public boolean existsByListIdAndItemId(Integer listId, Integer itemId) {
        return em.createQuery("select e from ShoppingList e where e.shoppingListByUser.id =? 1 and e.item.id =? 2",
                        ShoppingList.class)
                .setParameter(1, listId)
                .setParameter(2, itemId)
                .getResultStream()
                .findFirst().isPresent();
    }

    public ShoppingList findByListIdAndItemId(Integer listId, Integer itemId) {
        return em.createQuery("select e from ShoppingList e where e.shoppingListByUser.id =? 1 and e.item.id =? 2",
                ShoppingList.class)
                .setParameter(1, listId)
                .setParameter(2, itemId)
                .getSingleResult();
    }
}
