package com.sda.werehouse.unit303.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class loginController {

    @GetMapping("/login")
    public String login() {
        return "/login";
    }
}
