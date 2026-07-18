package com.example.demo.Controllers;

import com.example.demo.Entity.ServicePack.Module;
import com.example.demo.Entity.Enums.Status;
import com.example.demo.Entity.UserPack.User;
import com.example.demo.Repository.CardRepository;
import com.example.demo.Repository.ModuleRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class MainPageController {

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    ModuleService moduleService;

    @GetMapping("/home")
    public String main(Model model, @AuthenticationPrincipal UserDetails userDetails){
        User user = userRepository.findByUsername(userDetails.getUsername());
        Set<Module> modules = user.getModules();
        model.addAttribute("modules", modules);
        return "main";
    }

    @GetMapping("/home/allModules")
    public String allModules(Model model){
        model.addAttribute("publicModules", moduleRepository.findByStatus(Status.PUBLIC));
        return "allmodules";
    }

    @GetMapping("/create-module")
    public String createModule(Model model){
        model.addAttribute("module", new Module());
        return "createModule";
    }

    @PostMapping("/create-module")
    public String createModulePost(@ModelAttribute("module") Module module, @RequestParam Map<String, String> allParams, @AuthenticationPrincipal UserDetails userDetails){
        moduleService.createModule(module, allParams, userDetails);
        return "redirect:/home";
    }


}
