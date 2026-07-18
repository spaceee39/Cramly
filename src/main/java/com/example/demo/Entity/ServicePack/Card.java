package com.example.demo.Entity.ServicePack;

import com.example.demo.Entity.Enums.CardStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Сards")
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "theme")
    private String theme;

    @Column(name = "text")
    private String text;

    @Column(name = "owner")
    private String owner;
/*
    @ManyToOne
    @JoinColumn(name = "module_id")
    Module module;
*/
    @CreationTimestamp
    @Column(name = "createdAt", updatable = false)
    private LocalDateTime createdAt;

    private CardStatus cardStatus;

    public CardStatus getCardStatus(){
        if (cardStatus == null){
           return CardStatus.STOCK;
        } else {
            return cardStatus;
        }
    }

}
