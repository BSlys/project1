package com.sda.werehouse.unit303.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {

    Authentication authentication;
    public String authMessage;
    @GetMapping("/")
    public String index(Model model) {
        authentication= SecurityContextHolder.getContext().getAuthentication();
        authMessage=authentication.getName() + authentication.getAuthorities();
        model.addAttribute("authMessage", authMessage);
        return "index";
    }

    @GetMapping("/index")
    public String index2() {
        return "index";
    }
}
