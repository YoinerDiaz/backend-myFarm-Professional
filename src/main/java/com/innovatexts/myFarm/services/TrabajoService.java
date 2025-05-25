package com.innovatexts.myFarm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovatexts.myFarm.models.Trabajo;
import com.innovatexts.myFarm.repository.TrabajoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TrabajoService {
    
    @Autowired
    private TrabajoRepository trabajoRepository;

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
}
