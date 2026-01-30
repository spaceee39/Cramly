package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Сards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;

    @Column(name = "theme")
    private String theme;

    @Column(name = "text")
    private String text;
}
