package com.example.demo.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="Users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "Имя пользователя не может быть пустым")
    @Size(min = 5, message = "Имя пользователя должно содержать не менее 5 символов")
    private String username;

    @Column(name = "password")
    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 5, message = "Пароль должен содержать не менее 5 символов")
    private String password;

    @Transient
    private String confirmpassword;

    @OneToMany
    private Set<Role> roles = new HashSet();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public @Nullable String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
