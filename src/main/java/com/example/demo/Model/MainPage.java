package com.example.demo.Model;

import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class MainPage {

    @Autowired
    UserRepository userRepository;

    List<Module> modules = new ArrayList<>();

    public MainPage(String username){
        User user = userRepository.findByUsername(username);
        modules = (List<Module>) user.getModules();

    }
}
