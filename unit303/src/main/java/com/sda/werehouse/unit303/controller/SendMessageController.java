package com.sda.werehouse.unit303.controller;

import com.sda.werehouse.unit303.model.dto.MessageDto;
import com.sda.werehouse.unit303.model.dto.UserDto;
import com.sda.werehouse.unit303.repositories.UserRepo;
import com.sda.werehouse.unit303.service.MessageService;
import com.sda.werehouse.unit303.service.UserService;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class SendMessageController {
    public String m1 = "witaj";
    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;

    @GetMapping("/sendMessage")
    public String sendMessageTo(UserDto userDto, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MessageDto messageDto = new MessageDto();
        messageDto.sender = authentication.getName();
        messageDto.reciver = userDto.id;
        messageDto.message="witaj";
        model.addAttribute("message1", messageDto);
        return "sendMessage";
    }

    @PostMapping("/sendMessage")
    public String sendThis(MessageDto message1) {
        message1.setDate(LocalDateTime.now());
        messageService.addMessage(message1);
        return "redirect:/adduser";
    }

    @PostMapping("/markAsRead")
    public String readMessage(MessageDto messageDto) {
        messageService.markAsRead(messageDto);
        return "redirect:/index";
    }
}
