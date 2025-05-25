package com.innovatexts.myFarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.innovatexts.myFarm.models.Usuario;
import com.innovatexts.myFarm.services.UsuarioService;

import java.util.List;
import java.util.Optional;

@RestController
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
    public Usuario crear(@RequestBody Usuario usuario) {
        return usuarioService.guardarUsuario(usuario);
    }

    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Integer id, @RequestBody Usuario usuarioActualizado) {
        usuarioActualizado.setUid(id);
        return usuarioService.guardarUsuario(usuarioActualizado);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        usuarioService.eliminarUsuario(id);
    }
}
