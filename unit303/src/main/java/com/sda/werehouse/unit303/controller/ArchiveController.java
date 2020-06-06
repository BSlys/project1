package com.sda.werehouse.unit303.controller;

import com.sda.werehouse.unit303.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArchiveController {

    @Autowired
    public ArchiveService archiveService;

    @GetMapping("/archive")
    public String seeArchive(Model model) {
        model.addAttribute("archiveset", archiveService.archiveRepo.findAll());
        return "/archive";
    }
}
