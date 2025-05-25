package com.innovatexts.myFarm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.innovatexts.myFarm.models.Cultivo;
import com.innovatexts.myFarm.models.Usuario;
import com.innovatexts.myFarm.repository.CultivoRepository;
import com.innovatexts.myFarm.repository.UsuarioRepository;

@Service
public class CultivoService {
    @Autowired
    private CultivoRepository cultivoRepository;
    private UsuarioRepository usuarioRepository;

    public List<Cultivo> listarCultivos() {
        return cultivoRepository.findAll();
    }

    public Optional<Cultivo> obtenerCultivoPorId(Integer id) {
        return cultivoRepository.findById(id);
    }

public Cultivo guardarCultivo(Cultivo cultivo, Integer idAdministrador) {

        // 1. Buscar el usuario por ID
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(idAdministrador);

        if (usuarioOpt.isEmpty()) {
            throw new IllegalArgumentException("El usuario con ID " + idAdministrador + " no existe.");
        }

        Usuario administrador = usuarioOpt.get();

        // 2. Verificar que tenga el rol de ADMINISTRADOR
        if (!"ADMINISTRADOR".equalsIgnoreCase(administrador.getRol().getNombre())) {
            throw new IllegalArgumentException("El usuario con ID " + idAdministrador + " no tiene rol de ADMINISTRADOR.");
        }

        // 3. Asignar el administrador al cultivo
        cultivo.setAdministrador(administrador);

        // 4. Guardar el cultivo
        return cultivoRepository.save(cultivo);
    }

    public void eliminarInsumo(Integer id) {
        cultivoRepository.deleteById(id);
    }
}
