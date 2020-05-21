package com.sda.werehouse.unit303.controller;

import com.sda.werehouse.unit303.model.dto.UserDto;
import com.sda.werehouse.unit303.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/addUser")
    public ModelAndView users() {
        return new ModelAndView("users", "userToInsert", new UserDto());
    }

    @GetMapping("/users")
    public ModelAndView getUsers() {
        return new ModelAndView("getusers","allUsers",userService.getAllUsers());
    }
}
