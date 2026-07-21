package com.example.demo.Entity.ServicePack;

import com.example.demo.Entity.Enums.EducationClasterStatus;
import com.example.demo.Entity.UserPack.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table
public class EducationClaster {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id", nullable = false)
    private Module moduleId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EducationClasterStatus status;

    @OneToMany(mappedBy = "educationClaster", fetch = FetchType.EAGER)
    @JoinColumn(name = "education_subclasters", nullable = false)
    private List<EducationSubClaster> educationSubClasters = new ArrayList<>();


}
