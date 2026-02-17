package com.example.demo.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Modules")
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    @NotBlank(message = "Поле не должно быть пустым")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "author")
    private String author;

    public enum Status{
        PUBLIC, PRIVATE
    }

    @Column(name="status")
    private Status status;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "cardslist",
            joinColumns = @JoinColumn(name = "module_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    List<Card> cardList = new ArrayList<>();

    @Column(name = "сount")
    private int cardCount;

}
