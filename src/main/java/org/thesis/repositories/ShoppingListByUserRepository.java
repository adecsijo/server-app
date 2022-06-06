package org.thesis.repositories;

import org.thesis.entities.ShoppingListByUser;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ShoppingListByUserRepository extends Repository<ShoppingListByUser, Integer> {

    @Inject
    EntityManager em;

    protected ShoppingListByUserRepository() {
        super(ShoppingListByUser.class);
    }

    public List<ShoppingListByUser> findAllByUser(String username) {
        return em.createQuery("select e from ShoppingListByUser e where e.user.username =? 1",
                ShoppingListByUser.class)
                .setParameter(1, username)
                .getResultList();
    }

    public ShoppingListByUser findById(String username, Integer id) {
        return em.createQuery("select e from ShoppingListByUser e where e.user.username =? 1 and e.id =? 2",
                ShoppingListByUser.class)
                .setParameter(1, username)
                .setParameter(2, id)
                .getSingleResult();
    }

    public boolean existsByUserAndName(String username, String name) {
        return em.createQuery("select e from ShoppingListByUser e where e.user.username =? 1 and e.name =? 2",
                        ShoppingListByUser.class)
                .setParameter(1, username)
                .setParameter(2, name)
                .getResultStream()
                .findFirst().isPresent();
    }
}
