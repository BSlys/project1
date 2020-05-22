package com.sda.werehouse.unit303.controller;

import com.sda.werehouse.unit303.model.ItemWrapper;
import com.sda.werehouse.unit303.model.dto.ItemDto;
import com.sda.werehouse.unit303.model.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class inventoryController {

    @Autowired
    public ItemWrapper itemWrapper;

    @GetMapping("/inventory")
    public ModelAndView inventory() {
        return new ModelAndView("inventory", "ItemList", itemWrapper);
    }

    @PostMapping("/addItem")
    public String addItem(ItemDto itemDto) {
        itemWrapper.itemService.addItemToRepo(itemDto);
        return "redirect:/inventory";
    }

    @PostMapping("/deleteItem")
    public String deleteItemFromRepo(ItemDto itemDto) {
        itemWrapper.itemService.deleteItemFromRepo(itemDto);
        return "redirect:/inventory";
    }
}
