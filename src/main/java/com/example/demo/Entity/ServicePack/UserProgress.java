package com.example.demo.Entity.ServicePack;

import com.example.demo.Entity.Enums.LearningStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "UserProgress")
@Data
public class UserProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userName;

    @Column(name = "module_id", nullable = false)
    private Long module;

    @Column(name = "card_id", nullable = false)
    private Long card;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private LearningStatus status;

    }
