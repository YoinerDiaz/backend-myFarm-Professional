package com.innovatexts.myFarm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovatexts.myFarm.models.Cultivo;
import com.innovatexts.myFarm.models.Trabajo;
import com.innovatexts.myFarm.models.Usuario;
import com.innovatexts.myFarm.repository.CultivoRepository;
import com.innovatexts.myFarm.repository.TrabajoRepository;
import com.innovatexts.myFarm.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TrabajoService {

    @Autowired
    private TrabajoRepository trabajoRepository;
    @Autowired
    private CultivoRepository cultivoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Trabajo> listarTrabajos() {
        return trabajoRepository.findAll();
    }

    public Optional<Trabajo> obtenerTrabajoPorId(Integer id) {
        return trabajoRepository.findById(id);
    }

    public Trabajo guardarTrabajo(Trabajo trabajo) {
        return trabajoRepository.save(trabajo);
    }

    public void eliminarTrabajo(Integer id) {
        trabajoRepository.deleteById(id);
    }

    public Trabajo asignarCultivo(Integer idTrabajo, Integer idCultivo) {
        Trabajo trabajo = trabajoRepository.findById(idTrabajo)
                .orElseThrow(() -> new RuntimeException("Trabajo no encontrado"));

        Cultivo cultivo = cultivoRepository.findById(idCultivo)
                .orElseThrow(() -> new RuntimeException("Cultivo no encontrado"));

        trabajo.setCultivo(cultivo);
        return trabajoRepository.save(trabajo);
    }

    public Trabajo asignarTrabajadores(Integer idTrabajo, List<Integer> idsTrabajadores) {
        Trabajo trabajo = trabajoRepository.findById(idTrabajo)
                .orElseThrow(() -> new RuntimeException("Trabajo no encontrado"));

        List<Usuario> trabajadores = usuarioRepository.findAllById(idsTrabajadores);
        trabajo.setTrabajadores(trabajadores); // reemplaza los actuales, o usa addAll si es acumulativo
        return trabajoRepository.save(trabajo);
    }

}
