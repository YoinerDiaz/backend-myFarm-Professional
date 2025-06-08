package com.innovatexts.myFarm.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.innovatexts.myFarm.DTO.TrabajoInsumoResponseDTO;
import com.innovatexts.myFarm.services.TrabajoInsumoService;

@RestController
@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyRole('PATRON', 'ADMINISTRADOR')")
@RequestMapping("/api/trabajo-insumo")
public class TrabajoInsumoController {

    private final TrabajoInsumoService trabajoInsumoService;

    public TrabajoInsumoController(TrabajoInsumoService trabajoInsumoService) {
        this.trabajoInsumoService = trabajoInsumoService;
    }

    @GetMapping("/asignar/all") // Nueva URL para obtener todas las asignaciones con detalles
    public ResponseEntity<List<TrabajoInsumoResponseDTO>> getAllAsignaciones() {
        List<TrabajoInsumoResponseDTO> asignaciones = trabajoInsumoService.getAllAsignacionesWithDetails();
        return new ResponseEntity<>(asignaciones, HttpStatus.OK);
    }

    @PostMapping("/asignar")
    public ResponseEntity<String> asignar(@RequestBody TrabajoInsumoResponseDTO dto) {
        trabajoInsumoService.asignarInsumoATrabajo(dto);
        return ResponseEntity.ok("Insumo asignado correctamente.");
    }

    @PutMapping("/editar")
    public ResponseEntity<String> editar(@RequestBody TrabajoInsumoResponseDTO dto) {
        trabajoInsumoService.editarAsignacion(dto);
        return ResponseEntity.ok("Asignación editada correctamente.");
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        trabajoInsumoService.eliminarAsignacion(id);
        return ResponseEntity.ok("Asignación eliminada correctamente.");
    }
}
