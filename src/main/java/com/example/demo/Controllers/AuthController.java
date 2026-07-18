package com.example.demo.Controllers;

import com.example.demo.Entity.UserPack.User;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    UserService userService;


    @GetMapping("/login")
    public String loginget(@RequestParam(name = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Неверный логин или пароль!");
        }
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/register")
    public String regGet(Model model, @RequestParam(name = "error", required = false) String error) {
        model.addAttribute("user", new User());
        return "register";
    }


    @PostMapping("/register")
    public String regPost(@ModelAttribute("user") User user, Model model) {
        if (userService.hasReg(user)) {
            model.addAttribute("error", "Пользователь с таким именем уже существует.");
            return "register";
        } else {
            userService.regUser(user);
            return "redirect:/login?registered";
        }
    }
}



