package com.example.demo.Repository;

import com.example.demo.Model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, String> {
    List<Module> findByName(String name);
}
