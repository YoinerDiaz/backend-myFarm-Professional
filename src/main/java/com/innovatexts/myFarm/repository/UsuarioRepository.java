package com.innovatexts.myFarm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innovatexts.myFarm.models.Usuario;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    boolean existsByUsuario(String usuario);
    Optional<Usuario> findByUsuario(String usuario);
}