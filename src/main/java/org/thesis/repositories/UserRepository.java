package org.thesis.repositories;

import org.thesis.dtos.UserDto;
import org.thesis.entities.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class UserRepository {
    @Inject
    EntityManager em;

    @Transactional
    public List<User> getAllUser() {
        return em.createQuery("select e from User e").getResultList();
    }

    @Transactional
    public void saveUser(User user) {
        em.persist(user);
        em.flush();
    }

    @Transactional
    public User findUser(String username) {
        return em.find(User.class, username);
    }
}
