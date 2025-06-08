package com.innovatexts.myFarm.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.innovatexts.myFarm.DTO.TrabajoInsumoResponseDTO;
import com.innovatexts.myFarm.models.Insumo;
import com.innovatexts.myFarm.models.Trabajo;
import com.innovatexts.myFarm.models.TrabajoInsumo;
import com.innovatexts.myFarm.repository.InsumoRepository;
import com.innovatexts.myFarm.repository.TrabajoInsumoRepository;
import com.innovatexts.myFarm.repository.TrabajoRepository;

@Service
public class TrabajoInsumoService {

    private final TrabajoRepository trabajoRepository;
    private final InsumoRepository insumoRepository;
    private final TrabajoInsumoRepository trabajoInsumoRepository;

    public TrabajoInsumoService(
        TrabajoRepository trabajoRepository,
        InsumoRepository insumoRepository,
        TrabajoInsumoRepository trabajoInsumoRepository
    ) {
        this.trabajoRepository = trabajoRepository;
        this.insumoRepository = insumoRepository;
        this.trabajoInsumoRepository = trabajoInsumoRepository;
    }

    public List<TrabajoInsumoResponseDTO> getAllAsignacionesWithDetails() {
        List<TrabajoInsumo> asignaciones = trabajoInsumoRepository.findAll();
        
        // Mapea la lista de entidades a una lista de DTOs
        return asignaciones.stream().map(asignacion ->
            new TrabajoInsumoResponseDTO(
                asignacion.getId(),
                asignacion.getTrabajo().getNombre(), // Obtiene el nombre del trabajo
                asignacion.getTrabajo().getId(),     // Obtiene el ID del trabajo
                asignacion.getInsumo().getNombre(),  // Obtiene el nombre del insumo
                asignacion.getInsumo().getId(),      // Obtiene el ID del insumo
                asignacion.getCantidadUsada()
            )
        ).collect(Collectors.toList());
    }

    // Crear
    public void asignarInsumoATrabajo(TrabajoInsumoResponseDTO dto) {
        Trabajo trabajo = trabajoRepository.findById(dto.getTrabajoId()).orElseThrow();
        Insumo insumo = insumoRepository.findById(dto.getInsumoId()).orElseThrow();

        if (insumo.getStock() < dto.getCantidadUsada()) {
            throw new RuntimeException("Stock insuficiente.");
        }

        insumo.setStock(insumo.getStock() - dto.getCantidadUsada());

        TrabajoInsumo trabajoInsumo = new TrabajoInsumo();
        trabajoInsumo.setTrabajo(trabajo);
        trabajoInsumo.setInsumo(insumo);
        trabajoInsumo.setCantidadUsada(dto.getCantidadUsada());

        trabajoInsumoRepository.save(trabajoInsumo);
        insumoRepository.save(insumo);
    }

    // Editar
    public void editarAsignacion(TrabajoInsumoResponseDTO dto) {
        TrabajoInsumo trabajoInsumo = trabajoInsumoRepository.findById(dto.getId()).orElseThrow();
        Insumo insumo = trabajoInsumo.getInsumo();

        Integer cantidadAnterior = trabajoInsumo.getCantidadUsada();
        Integer diferencia = dto.getCantidadUsada() - cantidadAnterior;

        if (insumo.getStock() < diferencia) {
            throw new RuntimeException("Stock insuficiente para actualizar la asignación.");
        }

        trabajoInsumo.setCantidadUsada(dto.getCantidadUsada());
        insumo.setStock(insumo.getStock() - diferencia);

        trabajoInsumoRepository.save(trabajoInsumo);
        insumoRepository.save(insumo);
    }

    // Eliminar
    public void eliminarAsignacion(Integer id) {
        TrabajoInsumo trabajoInsumo = trabajoInsumoRepository.findById(id).orElseThrow();
        Insumo insumo = trabajoInsumo.getInsumo();

        // Revertir stock
        insumo.setStock(insumo.getStock() + trabajoInsumo.getCantidadUsada());

        trabajoInsumoRepository.delete(trabajoInsumo);
        insumoRepository.save(insumo);
    }
}
