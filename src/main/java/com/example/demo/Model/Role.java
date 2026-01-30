package com.example.demo.Model;

import jakarta.persistence.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;


@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name="user_id")
    private long user_id;

    @Column(name = "name")
    private String name;

    @Override
    public @Nullable String getAuthority() {
        return name;
    }
}
