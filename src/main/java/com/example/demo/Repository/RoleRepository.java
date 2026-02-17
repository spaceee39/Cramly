package com.example.demo.Repository;

import com.example.demo.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository

public interface RoleRepository extends JpaRepository<Role, Long> {
    Set<Role> findByUserId(Long userid);
}
