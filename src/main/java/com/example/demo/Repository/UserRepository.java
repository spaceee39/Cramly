package com.example.demo.Repository;

import com.example.demo.Entity.UserPack.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String name);
 }
