package com.example.demo.Repository;

import com.example.demo.Entity.ServicePack.UserProgress;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProgressRepository extends JpaRepository<UserProgress,Long> {
    List<UserProgress> findByUserNameAndModule(String userName, Long moduleId);

    @Transactional
    void deleteByUserNameAndModule(String userName, Long moduleId);
}
