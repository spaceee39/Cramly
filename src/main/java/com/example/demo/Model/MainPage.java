package com.example.demo.Model;

import com.example.demo.Repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class MainPage {
    List<Module> modules = new ArrayList<>();

    @Autowired
    ModuleRepository moduleRepository;

    public MainPage(String username){
        modules = moduleRepository.findByName(username);
    }
}
