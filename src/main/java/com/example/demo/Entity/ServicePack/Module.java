package com.example.demo.Entity.ServicePack;

import com.example.demo.Entity.Enums.CardStatus;
import com.example.demo.Entity.Enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Modules")
@NoArgsConstructor
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "Поле не должно быть пустым")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "author")
    private String author;

    @Column(name="status")
    private Status status;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "cardslist",
            joinColumns = @JoinColumn(name = "module_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    List<Card> cardList = new ArrayList<>();

    @Column(name = "сount")
    private int cardCount;

    @CreationTimestamp
    @Column(name = "createdAt", updatable = false)
    private LocalDateTime createdAt;

    public void editModuleinfo (Module moduleD){
        name = moduleD.getName();
        description = moduleD.getDescription();
        status = moduleD.getStatus();
        for (Card card : moduleD.getCardList() ){
            if(card.getCardStatus().equals(CardStatus.EDITED)){
                card.setOwner(author);
            }
            if(card.getCardStatus().equals(CardStatus.DELETED)){
              moduleD.getCardList().remove(card);
            }
        }
        cardList = moduleD.getCardList();
        cardCount = cardList.size();
    }


}
