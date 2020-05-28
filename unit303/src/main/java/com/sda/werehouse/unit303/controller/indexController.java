package com.sda.werehouse.unit303.controller;


import com.sda.werehouse.unit303.model.dto.MessageDto;
import com.sda.werehouse.unit303.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class indexController {

    @Autowired
    public MessageService messageService;


    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authMessage = authentication.getName() + authentication.getAuthorities();
        model.addAttribute("authMessage", authMessage);
        List<MessageDto> myMessages = messageService.seeMessagesForMe();
        model.addAttribute("MyMessages", myMessages);
        return "index";
    }
}
