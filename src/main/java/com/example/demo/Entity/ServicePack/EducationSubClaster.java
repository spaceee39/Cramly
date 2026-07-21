package com.example.demo.Entity.ServicePack;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class EducationSubClaster {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private EducationClaster educationClaster;

    /*  0 - Дается тестовый вариант ответа
        1 - Дается ввод ответа
        2 - Карточка изучена
    */
    @Column(name = "category_studied")
    private Integer category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card", nullable = false)
    private Card card;
}
