package com.sda.werehouse.unit303.controller;

import com.sda.werehouse.unit303.model.ItemWrapper;
import com.sda.werehouse.unit303.model.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class inventoryController {

    @GetMapping("/inventory")
    public ModelAndView inventory() {
        return new ModelAndView("inventory", "ItemList", new ItemWrapper());
    }
}
