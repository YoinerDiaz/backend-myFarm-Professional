package com.innovatexts.myFarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.innovatexts.myFarm.DTO.TrabajoDTO;
import com.innovatexts.myFarm.models.Trabajo;
import com.innovatexts.myFarm.services.TrabajoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trabajos")
@CrossOrigin(origins = "*") // Habilita peticiones desde el frontend
public class TrabajoController {

    @Autowired
    private TrabajoService trabajoService;

    @GetMapping
    public List<Trabajo> listar() {
        return trabajoService.listarTrabajos();
    }

    @GetMapping("/{id}")
    public Optional<Trabajo> obtenerPorId(@PathVariable Integer id) {
        return trabajoService.obtenerTrabajoPorId(id);
    }

    @PostMapping
    public Trabajo crear(@RequestBody Trabajo trabajo) {
        return trabajoService.guardarTrabajo(trabajo);
    }

    @PutMapping("/{id}")
    public Trabajo actualizar(@PathVariable Integer id, @RequestBody TrabajoDTO trabajoActualizado) {

        Optional<Trabajo> trabajoOpcional = trabajoService.obtenerTrabajoPorId(id);
        Trabajo trabajoExistente = null;

        if (trabajoOpcional.isPresent()) {
            trabajoExistente = trabajoOpcional.get();
        }

        if (trabajoActualizado.getNombre() != null) {
            trabajoExistente.setNombre(trabajoActualizado.getNombre());
        }
        if (trabajoActualizado.getDetalle() != null) {
            trabajoExistente.setDetalle(trabajoActualizado.getDetalle());
        }
        if (trabajoActualizado.getId_cultivo() != null) {
            trabajoExistente.setId_cultivo(trabajoActualizado.getId_cultivo());
        }
        if (Math.abs(trabajoActualizado.getInversion() - trabajoExistente.getInversion()) > 0.0001f) {
            trabajoExistente.setInversion(trabajoActualizado.getInversion());
        }
        if (trabajoActualizado.getFecha_inicio() != null) {
            trabajoExistente.setFecha_inicio(trabajoActualizado.getFecha_inicio());
        }
        if (trabajoActualizado.getFecha_fin() != null) {
            trabajoExistente.setFecha_fin(trabajoActualizado.getFecha_fin());
        }

        // Guardar el usuario actualizado
        return trabajoService.guardarTrabajo(trabajoExistente);

    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        trabajoService.eliminarTrabajo(id);
    }
}
