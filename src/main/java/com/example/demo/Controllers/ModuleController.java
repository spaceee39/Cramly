package com.example.demo.Controllers;

import com.example.demo.Entity.ServicePack.Module;
import com.example.demo.Repository.CardRepository;
import com.example.demo.Repository.ModuleRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.LearningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/module/{id}")
public class ModuleController {

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    LearningService learningService;


    @GetMapping
    public String viewModule(@PathVariable Long id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Optional<Module> moduleOptional = moduleRepository.findById(id);
         if (moduleOptional.isPresent()) {
            model.addAttribute("module", moduleOptional.get()); // Передаём сам объект
            model.addAttribute("userDetails", userDetails);
            return "moduleView";
        } else {
            return "redirect:/home";
        }
    }


    @GetMapping("/edit")
    public String editModuleget(@PathVariable Long id, Model model, @AuthenticationPrincipal UserDetails userDetails){
        Optional<Module> module = moduleRepository.findById(id);
        if(module.get().getAuthor().equals(userDetails.getUsername())){
            model.addAttribute("module", module.get());
            return "edit-Module";
        } else {
            return "redirect:/module/"+id;
        }
    }

    @GetMapping("/learn")
    public String learnFlashCards(@PathVariable Long id,@RequestParam("mode") String mode, Model model, @AuthenticationPrincipal UserDetails userDetails){
        Module module = learningService.moduleForLearning(userDetails.getUsername(), id);
        if(module.getAuthor().equals(userDetails.getUsername())){
            if (mode.equals("cards")) {
                model.addAttribute("module", module);
                return "LearningFlashCards";
            } else {
                return "redirect:/home";
            }
        }
        return "error";
    }
}
