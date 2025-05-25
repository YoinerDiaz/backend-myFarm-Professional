package com.innovatexts.myFarm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovatexts.myFarm.models.Insumo;
import com.innovatexts.myFarm.repository.InsumoRepository;


@Service
public class InsumoService {
    @Autowired
    private InsumoRepository insumoRepository;

    public List<Insumo> listarInsumos() {
        return insumoRepository.findAll();
    }

    public Optional<Insumo> obtenerInsumoPorId(Integer id) {
        return insumoRepository.findById(id);
    }

    public Insumo guardarInsumo(Insumo insumo) {
        return insumoRepository.save(insumo);
    }

    public void eliminarInsumo(Integer id) {
        insumoRepository.deleteById(id);
    }
}
