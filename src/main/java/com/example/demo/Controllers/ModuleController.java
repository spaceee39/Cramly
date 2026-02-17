package com.example.demo.Controllers;

import com.example.demo.Model.Module;
import com.example.demo.Repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/module")
public class ModuleController {

    @Autowired
    ModuleRepository moduleRepository;


    @GetMapping("/{id}")
    public String viewModule(@PathVariable Long id, Model model) {
        Optional<Module> moduleOptional = moduleRepository.findById(id);
        if (moduleOptional.isPresent()) {
            model.addAttribute("module", moduleOptional.get()); // Передаём сам объект
            return "moduleView";
        } else {
            return "redirect:/home";
        }
    }
}
