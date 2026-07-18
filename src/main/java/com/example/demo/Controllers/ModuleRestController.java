package com.example.demo.Controllers;


import com.example.demo.DTO.ModuleDto;
import com.example.demo.DTO.Request.CompleteLearningDtoRequest;
import com.example.demo.Service.LearningService;
import com.example.demo.Service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

import javax.swing.text.html.HTMLDocument;

@RestController
@RequestMapping("/api/")
public class ModuleRestController {

    @Autowired
    ModuleService moduleService;

    @Autowired
    LearningService learningService;

    @PostMapping("modules/edit")
    public ResponseEntity editModuleEndpoint(@RequestBody String module, @AuthenticationPrincipal UserDetails userDetails){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ModuleDto moduleDto = objectMapper.readValue(module, ModuleDto.class);
            moduleService.editModuleServiceTransaction(moduleDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid Request");
        }
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }

    @PostMapping("/module/{id}/learn/complete")
    public ResponseEntity learningFlashCards(@RequestBody String body, @AuthenticationPrincipal UserDetails userDetails){
        ObjectMapper mapper = new ObjectMapper();
        CompleteLearningDtoRequest completeLearningDtoRequest = mapper.readValue(body, CompleteLearningDtoRequest.class);
        learningService.UserProgressEdit(userDetails.getUsername(), completeLearningDtoRequest);
        return ResponseEntity.status(HttpStatus.OK).body("200");
    }
}
