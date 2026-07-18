package com.example.demo.Entity.ServicePack;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ModuleProgress {
    private short learningPercent;
    List<Card> cardList = new ArrayList<>();
}
