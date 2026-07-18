package com.example.demo.Repository;

import com.example.demo.Entity.ServicePack.Module;
import com.example.demo.Entity.Enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    List<Module>findByStatus(Status status);
}
