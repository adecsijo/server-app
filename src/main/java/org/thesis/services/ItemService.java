package org.thesis.services;

import org.thesis.repositories.ItemRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ItemService {

    @Inject
    ItemRepository itemRepository;
}
