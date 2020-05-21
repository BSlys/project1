package com.sda.werehouse.unit303.model;

import com.sda.werehouse.unit303.model.entity.Item;
import com.sda.werehouse.unit303.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemWrapper {
    public Item item = new Item();

    @Autowired
    public ItemService itemService;
}
