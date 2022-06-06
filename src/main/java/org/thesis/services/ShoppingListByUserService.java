package org.thesis.services;

import org.thesis.repositories.ShoppingListByUserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ShoppingListByUserService {

    @Inject
    ShoppingListByUserRepository shoppingListByUserRepository;
}
