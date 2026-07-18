package com.example.demo.Service;

import com.example.demo.Entity.UserPack.Role;
import com.example.demo.Entity.UserPack.User;
import com.example.demo.Repository.RoleRepository;
import com.example.demo.Repository.UserRepository;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService implements UserDetailsService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user != null) {
            Set<Role> roleList = roleRepository.findByUserId(user.getId());
            user.setRoles(roleList);
        } else {
            throw new UsernameNotFoundException("Пользователь с таким именем не найден.");
        }
        return user;
    }

    public boolean hasReg(@NotBlank(message = "Поля не должны быть пустыми") User user){
        if (userRepository.findByUsername(user.getUsername()) == null){
            return false;
        } else return true;
    }

    public void regUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }


}
