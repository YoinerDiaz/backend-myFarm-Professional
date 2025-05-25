package com.innovatexts.myFarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innovatexts.myFarm.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}