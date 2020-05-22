package com.sda.werehouse.unit303.model;

import com.sda.werehouse.unit303.model.dto.ItemDto;
import com.sda.werehouse.unit303.model.entity.Item;
import com.sda.werehouse.unit303.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemWrapper {
    public ItemDto itemDto = new ItemDto();

    @Autowired
    public ItemService itemService;
}
