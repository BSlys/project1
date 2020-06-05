package com.sda.werehouse.unit303.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FallbackController {
    @GetMapping("/fallback")
    public String fallback(String message, Model model) {
        model.addAttribute("message", message);
        return "/fallback";
    }
}
