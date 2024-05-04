package org.thesis.repositories;

import org.thesis.entities.ShoppingList;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ShoppingListRepository extends Repository<ShoppingList, Integer> {

    @Inject
    EntityManager em;

    protected ShoppingListRepository() {
        super(ShoppingList.class);
    }

    public List<ShoppingList> findAllByUser(String username) {
        return em.createQuery("select e from ShoppingList e where e.user.username =? 1",
                ShoppingList.class)
                .setParameter(1, username)
                .getResultList();
    }

    public boolean existsByUserAndName(String username, String name) {
        return em.createQuery("select e from ShoppingList e where e.user.username =? 1 and e.name =? 2",
                        ShoppingList.class)
                .setParameter(1, username)
                .setParameter(2, name)
                .getResultStream()
                .findFirst().isPresent();
    }
}
