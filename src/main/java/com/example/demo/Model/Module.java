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
    long id;

    @Column(name = "name")
    @NotBlank(message = "Поле не должно быть пустым")
    String name;

    @Column(name = "description")
    String description;

    @OneToMany(mappedBy = "module",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    List<Card> cardList = new ArrayList<>();

}
