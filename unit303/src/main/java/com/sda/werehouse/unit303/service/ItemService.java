package com.sda.werehouse.unit303.service;

import com.sda.werehouse.unit303.model.entity.Item;
import com.sda.werehouse.unit303.repositories.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemService {

    @Autowired
    public ItemRepo itemRepo;

    public void addItemToRepo(Item item) {
        itemRepo.save(item);
    }
}
