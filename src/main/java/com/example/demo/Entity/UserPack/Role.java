package com.example.demo.Entity.UserPack;

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
    private long userId;

    @Column(name = "name")
    private String name;

    @Override
    public @Nullable String getAuthority() {
        return name;
    }
}
