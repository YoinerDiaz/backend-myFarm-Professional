package com.innovatexts.myFarm.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovatexts.myFarm.models.Proveedor;
import com.innovatexts.myFarm.repository.ProveedorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {
    
     @Autowired
    private ProveedorRepository proveedorRepository;

    public List<Proveedor> listarProveedors() {
        return proveedorRepository.findAll();
    }

    public Optional<Proveedor> obtenerProveedorPorId(Integer id) {
        return proveedorRepository.findById(id);
    }

    public Proveedor guardarProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public void eliminarProveedor(Integer id) {
        proveedorRepository.deleteById(id);
    }
}
