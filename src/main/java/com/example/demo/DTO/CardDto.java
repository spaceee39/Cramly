package com.example.demo.DTO;

import com.example.demo.Entity.Enums.CardStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CardDto {

    private Long id;
    private String theme;
    private String text;
    private String owner;
    private LocalDateTime createdAt;
    private CardStatus cardStatus;

}
