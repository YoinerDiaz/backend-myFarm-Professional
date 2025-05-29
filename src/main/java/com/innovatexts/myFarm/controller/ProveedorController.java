package com.innovatexts.myFarm.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import com.innovatexts.myFarm.DTO.ProveedorDTO;
import com.innovatexts.myFarm.models.Proveedor;
import com.innovatexts.myFarm.services.ProveedorService;

@RestController
@PreAuthorize("hasRole('PATRON')")
@RequestMapping("/api/proveedores")
@CrossOrigin(origins = "*")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public List<Proveedor> listar() {
        return proveedorService.listarProveedors();
    }

    @GetMapping("/{id}")
    public Optional<Proveedor> obtenerPorId(@PathVariable Integer id) {
        return proveedorService.obtenerProveedorPorId(id);
    }

    @PostMapping
    public Proveedor crear(@RequestBody Proveedor proveedor) {
        return proveedorService.guardarProveedor(proveedor);
    }

    @PutMapping("/{id}")
    public Proveedor actualizar(@PathVariable Integer id, @RequestBody ProveedorDTO proveedorActualizado) {

        Optional<Proveedor> proveedorOpcional = proveedorService.obtenerProveedorPorId(id);
        Proveedor proveedorExistente = null;

        if (proveedorOpcional.isPresent()) {
            proveedorExistente = proveedorOpcional.get();
        }

        if (proveedorActualizado.getNombre() != null) {
            proveedorExistente.setNombre(proveedorActualizado.getNombre());
        }
        if (proveedorActualizado.getContacto() != null) {
            proveedorExistente.setContacto(proveedorActualizado.getContacto());
        }
        
        if (proveedorActualizado.getDireccion() != null) {
            proveedorExistente.setDireccion(proveedorActualizado.getDireccion());
        }
        // Guardar el usuario actualizado
        return proveedorService.guardarProveedor(proveedorExistente);

    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        proveedorService.eliminarProveedor(id);
    }
    
}
