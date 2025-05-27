package com.innovatexts.myFarm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.innovatexts.myFarm.models.Cultivo;
import com.innovatexts.myFarm.DTO.CultivoDTO;
import com.innovatexts.myFarm.models.Usuario;
import com.innovatexts.myFarm.repository.CultivoRepository;
import com.innovatexts.myFarm.repository.UsuarioRepository;

@Service
public class CultivoService {
    
    private CultivoRepository cultivoRepository;
    private UsuarioRepository usuarioRepository;

    @Autowired
    public CultivoService(CultivoRepository cultivoRepository, UsuarioRepository usuarioRepository) {
        this.cultivoRepository = cultivoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Cultivo> listarCultivos() {
        return cultivoRepository.findAll();
    }

    public Optional<Cultivo> obtenerCultivoPorId(Integer id) {
        return cultivoRepository.findById(id);
    }

public Cultivo guardarCultivo(CultivoDTO cultivodto) {

        // 1. Buscar el usuario por ID
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(cultivodto.getAdministrador());

        if (usuarioOpt.isEmpty()) {
            throw new IllegalArgumentException("El usuario con ID " + cultivodto.getAdministrador() + " no existe.");
        }

        Usuario administrador = usuarioOpt.get();

        // 2. Verificar que tenga el rol de ADMINISTRADOR
        if (!"ADMINISTRADOR".equalsIgnoreCase(administrador.getRol().getNombre())) {
            throw new IllegalArgumentException("El usuario con ID " + administrador.getUid() + " no tiene rol de ADMINISTRADOR.");
        }

        /// Construir el objeto Cultivo
        Cultivo cultivo = new Cultivo();
        cultivo.setNombre(cultivodto.getNombre());
        cultivo.setTipo(cultivodto.getTipo());
        cultivo.setVariedad(cultivodto.getVariedad());
        cultivo.setFecha_siembra(cultivodto.getFecha_siembra());
        cultivo.setHectareas(cultivodto.getHectareas());
        cultivo.setUbicacion(cultivodto.getUbicacion());
        cultivo.setAdministrador(administrador);
        

        // 4. Guardar el cultivo
        return cultivoRepository.save(cultivo);
    }

    public void eliminarCultivo(Integer id) {
        cultivoRepository.deleteById(id);
    }

    public Cultivo editarCultivo(Cultivo cultivo) {
    return cultivoRepository.save(cultivo);
}
}
