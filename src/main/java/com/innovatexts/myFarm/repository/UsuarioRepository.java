package com.innovatexts.myFarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innovatexts.myFarm.models.Usuario;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}