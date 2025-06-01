package com.innovatexts.myFarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.innovatexts.myFarm.DTO.RegistroUsuarioDTO;
import com.innovatexts.myFarm.DTO.UsuarioDTO;
import com.innovatexts.myFarm.models.Usuario;
import com.innovatexts.myFarm.services.UsuarioService;

import java.util.List;
import java.util.Optional;

@RestController
@PreAuthorize("hasAnyRole('PATRON', 'ADMINISTRADOR')")
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*") // Habilita peticiones desde el frontend
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public Optional<Usuario> obtenerPorId(@PathVariable Integer id) {
        return usuarioService.obtenerUsuarioPorId(id);
    }

    @PostMapping
    public Usuario crear(@RequestBody RegistroUsuarioDTO usuario) {
        return usuarioService.registrarUsuario(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario (@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuarioActualizado = usuarioService.actualizarUsuario(usuarioDTO, id);
        return ResponseEntity.ok(usuarioActualizado);
}

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        usuarioService.eliminarUsuario(id);
    }
}
