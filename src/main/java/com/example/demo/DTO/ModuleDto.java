package com.example.demo.DTO;


import com.example.demo.Entity.Enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

// Module request dto for change module
@Data
@NoArgsConstructor
public class ModuleDto {

    private Long id;
    private String name;
    private String description;
    private Status status;
    private List<CardDto> cards = new ArrayList<>();

    @Override
    public String toString() {
        return "Id: " + id +
                "\nName: " + name +
                "\nDescription: " + description +
                "\nStatus: " + status +
                "\nCards count: " + (cards != null ? cards.size() : 0)+
                "Cards: "+ cards.toString();
    }

}
