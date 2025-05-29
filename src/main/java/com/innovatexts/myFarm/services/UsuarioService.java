package com.innovatexts.myFarm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.innovatexts.myFarm.DTO.RegistroUsuarioDTO;
import com.innovatexts.myFarm.models.Rol;
import com.innovatexts.myFarm.models.Usuario;
import com.innovatexts.myFarm.repository.RolRepository;
import com.innovatexts.myFarm.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

     @Autowired
    private PasswordEncoder passwordEncoder;

     @Autowired
    private RolRepository rolRepository;

    @Transactional
    public Usuario registrarUsuario(RegistroUsuarioDTO registroDTO) {
        // Verificar si el usuario ya existe
        if (usuarioRepository.existsByUsuario(registroDTO.getUsuario())) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }

        Rol rol = rolRepository.findById(registroDTO.getRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

         // Crear el nuevo usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsuario(registroDTO.getUsuario());
        nuevoUsuario.setPassword(passwordEncoder.encode(registroDTO.getPassword())); // Encriptamos la contraseña
        nuevoUsuario.setNombre(registroDTO.getNombre());
        nuevoUsuario.setContacto(registroDTO.getContacto());
        nuevoUsuario.setRol(rol);

        // Guardar el usuario
        return usuarioRepository.save(nuevoUsuario);

    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
    }
}
