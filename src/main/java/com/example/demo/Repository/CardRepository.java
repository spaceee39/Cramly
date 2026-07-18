package com.example.demo.Repository;

import com.example.demo.Entity.ServicePack.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

}
