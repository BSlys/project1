package com.sda.werehouse.unit303.controller;

import com.sda.werehouse.unit303.repositories.MessageRepo;
import com.sda.werehouse.unit303.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReadMessagesController {
    @Autowired
    public MessageService messageService;

    @GetMapping("/readmessages")
    public String seeOldMessages(Model model) {
        model.addAttribute("oldmesaages",messageService.seeMessagesForMe(true));
        model.addAttribute("mymessages", messageService.seeMessagesByMe());
        return "/readmessages";
    }
}
