package com.innovatexts.myFarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innovatexts.myFarm.models.TrabajoInsumo;

@Repository
public interface TrabajoInsumoRepository extends JpaRepository<TrabajoInsumo, Integer>{
    
}
