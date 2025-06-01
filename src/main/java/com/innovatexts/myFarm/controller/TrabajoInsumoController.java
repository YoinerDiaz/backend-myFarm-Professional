package com.innovatexts.myFarm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.innovatexts.myFarm.DTO.TrabajoInsumoDTO;
import com.innovatexts.myFarm.services.TrabajoInsumoService;

@RestController
@PreAuthorize("hasAnyRole('PATRON', 'ADMINISTRADOR')")
@RequestMapping("/api/trabajo-insumo")
public class TrabajoInsumoController {

    private final TrabajoInsumoService trabajoInsumoService;

    public TrabajoInsumoController(TrabajoInsumoService trabajoInsumoService) {
        this.trabajoInsumoService = trabajoInsumoService;
    }

    @PostMapping("/asignar")
    public ResponseEntity<String> asignar(@RequestBody TrabajoInsumoDTO dto) {
        trabajoInsumoService.asignarInsumoATrabajo(dto);
        return ResponseEntity.ok("Insumo asignado correctamente.");
    }

    @PutMapping("/editar")
    public ResponseEntity<String> editar(@RequestBody TrabajoInsumoDTO dto) {
        trabajoInsumoService.editarAsignacion(dto);
        return ResponseEntity.ok("Asignación editada correctamente.");
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        trabajoInsumoService.eliminarAsignacion(id);
        return ResponseEntity.ok("Asignación eliminada correctamente.");
    }
}
