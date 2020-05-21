package com.sda.werehouse.unit303.controller;

import com.sda.werehouse.unit303.model.UsersWrapper;
import com.sda.werehouse.unit303.model.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UsersWrapper usersWrapper;

    //@Autowired
    //public UserService userService;

    @GetMapping("/adduser")
    public ModelAndView users() {
        return new ModelAndView("adduser", "userListAndInput", usersWrapper);
    }

    @PostMapping("/adduser")
    public String addUser(UserDto userDto) {
        usersWrapper.userService.addUserToRepo(userDto);
        return "redirect:/adduser";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(UserDto userDto) {
        usersWrapper.userService.deleteUserFromRepo(userDto);
        return "redirect:/adduser";
    }

}
