package com.sda.werehouse.unit303.controller;

import com.sda.werehouse.unit303.model.dto.UserDto;
import com.sda.werehouse.unit303.service.MessageService;
import com.sda.werehouse.unit303.service.OrderService;
import com.sda.werehouse.unit303.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {


    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private MessageService messageService;

    @GetMapping("/adduser")
    public String users(Model model) {
        model.addAttribute("userDto", new UserDto());
        model.addAttribute("userList", userService.getAllUsers());
        return "adduser";
    }



    @PostMapping("/adduserI")
    public String addUser(@ModelAttribute("userDto") UserDto userDto) {
        userService.addUserToRepo(userDto);
        return "redirect:/adduser";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(UserDto userDto) {
        if (orderService.deleteOrderForUser(userDto)) {
            messageService.deleteMessagesFor(userDto.getId());
            userService.deleteUserFromRepo(userDto);
        } else {
            String message = "Uzytkownik ma wydane zamowienia";
            return "redirect:/fallback?message=" + message;
        }

        return "redirect:/adduser";
    }

}
