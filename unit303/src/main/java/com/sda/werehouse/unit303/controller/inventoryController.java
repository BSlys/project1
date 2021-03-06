package com.sda.werehouse.unit303.controller;

import com.sda.werehouse.unit303.model.dto.ItemDto;
import com.sda.werehouse.unit303.model.entity.Item;
import com.sda.werehouse.unit303.model.entity.OrderEnt;
import com.sda.werehouse.unit303.service.ItemService;
import com.sda.werehouse.unit303.service.OrderService;
import com.sda.werehouse.unit303.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class inventoryController {

    @Autowired
    public ItemService itemService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @GetMapping("/inventory")
    public String inventory(Model model) {
        model.addAttribute("ItemList", itemService.getAllItems());
        model.addAttribute("ItemDto", new ItemDto());
        model.addAttribute("Message", itemService.getMessage());
        itemService.setMessage("oczekiwanie na instrukcje");
        return "inventory";
    }

    @PostMapping("/addItem")
    public String addItem(ItemDto itemDto) {
        itemService.addItemToRepo(itemDto);
        return "redirect:/inventory";
    }

    @PostMapping("/deleteItem")
    public String deleteItemFromRepo(ItemDto itemDto) {
        if (itemService.deleteItemFromRepo(itemDto)) {
            orderService.deleteOrderForItem(itemDto);
        }
        return "redirect:/inventory";
    }
}
