package com.sda.werehouse.unit303.controller;

import com.sda.werehouse.unit303.model.dto.UserDto;
import com.sda.werehouse.unit303.repositories.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SendMessageController {
    private UserRepo userRepo;

    @PostMapping("/sendMessage")
    public String sendThis(String text) {
        //userRepo.getOne(1L).setPersonalMessege(text);
        return "redirect:/index";
    }
}
