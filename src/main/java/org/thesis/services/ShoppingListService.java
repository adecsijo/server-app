package org.thesis.services;

import org.thesis.repositories.ShoppingListRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ShoppingListService {

    @Inject
    ShoppingListRepository shoppingListRepository;
}
