package com.example.demo.Controllers;

import com.example.demo.Model.MainPage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    @GetMapping
    public String main(Model model,@AuthenticationPrincipal UserDetails userDetails){
        model.addAttribute("mainpage", new MainPage(userDetails.getUsername()));
        return "main";
    }
}
