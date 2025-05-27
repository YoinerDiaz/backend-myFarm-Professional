package com.innovatexts.myFarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innovatexts.myFarm.models.CompraInsumo;
@Repository
public interface CompraInsumoRepository extends JpaRepository<CompraInsumo, Integer>{

}