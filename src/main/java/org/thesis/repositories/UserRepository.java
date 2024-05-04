package org.thesis.repositories;

import org.thesis.entities.User;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository extends Repository<User, String>{

    protected UserRepository() {
        super(User.class);
    }
}
