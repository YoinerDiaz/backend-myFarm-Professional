package com.innovatexts.myFarm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innovatexts.myFarm.models.Trabajo;

@Repository
public interface TrabajoRepository extends JpaRepository<Trabajo, Integer>{
    
}