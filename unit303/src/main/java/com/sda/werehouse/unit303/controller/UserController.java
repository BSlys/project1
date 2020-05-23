package com.sda.werehouse.unit303.controller;

import com.sda.werehouse.unit303.model.dto.UserDto;
import com.sda.werehouse.unit303.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("/adduser")
    public String users(Model model) {
        model.addAttribute("userDto", new UserDto());
        model.addAttribute("userList", userService.getAllUsers());
        return "adduser";
    }

    @PostMapping("/adduser")
    public String addUser(@ModelAttribute("userDto") UserDto userDto) {
        userService.addUserToRepo(userDto);
        return "redirect:/adduser";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(UserDto userDto) {
        userService.deleteUserFromRepo(userDto);
        return "redirect:/adduser";
    }

}
