package com.innovatexts.myFarm.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.innovatexts.myFarm.DTO.CultivoDTO;
import com.innovatexts.myFarm.models.Cultivo;
import com.innovatexts.myFarm.models.Usuario;
import com.innovatexts.myFarm.repository.UsuarioRepository;
import com.innovatexts.myFarm.services.CultivoService;

@RestController
@RequestMapping("/api/cultivos")
@CrossOrigin(origins = "*")
public class CultivoController {
    private CultivoService cultivoService;
    private UsuarioRepository usuarioRepository;

    @Autowired
    public CultivoController(CultivoService cultivoService, UsuarioRepository usuarioRepository) {
        this.cultivoService = cultivoService;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<Cultivo> listar() {
        return cultivoService.listarCultivos();
    }

    @GetMapping("/{id}")
    public Optional<Cultivo> obtenerPorId(@PathVariable Integer id) {
        return cultivoService.obtenerCultivoPorId(id);
    }

    @PostMapping
    public Cultivo crear(@RequestBody CultivoDTO cultivodto) {


        return cultivoService.guardarCultivo(cultivodto);
    }

    @PutMapping("/{id}")
public Cultivo actualizar(@PathVariable Integer id, @RequestBody CultivoDTO cultivoActualizado) {

    Optional<Cultivo> cultivoOpcional = cultivoService.obtenerCultivoPorId(id);

    if (cultivoOpcional.isEmpty()) {
        throw new IllegalArgumentException("El cultivo con ID " + id + " no existe.");
    }

    Cultivo cultivoExistente = cultivoOpcional.get();

    // Actualizar campos si vienen en el DTO
    if (cultivoActualizado.getNombre() != null) {
        cultivoExistente.setNombre(cultivoActualizado.getNombre());
    }

    if (cultivoActualizado.getTipo() != null) {
        cultivoExistente.setTipo(cultivoActualizado.getTipo());
    }

    if (cultivoActualizado.getVariedad() != null) {
        cultivoExistente.setVariedad(cultivoActualizado.getVariedad());
    }

    if (cultivoActualizado.getFecha_siembra() != null) {
        cultivoExistente.setFecha_siembra(cultivoActualizado.getFecha_siembra());
    }

    if (cultivoActualizado.getHectareas() != null) {
        cultivoExistente.setHectareas(cultivoActualizado.getHectareas());
    }

    if (cultivoActualizado.getUbicacion() != null) {
        cultivoExistente.setUbicacion(cultivoActualizado.getUbicacion());
    }

    // Actualizar el administrador si viene un idAdministrador nuevo
    if (cultivoActualizado.getAdministrador() != null) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(cultivoActualizado.getAdministrador());

        if (usuarioOpt.isEmpty()) {
            throw new IllegalArgumentException("El usuario con ID " + cultivoActualizado.getAdministrador() + " no existe.");
        }

        Usuario administrador = usuarioOpt.get();

        if (!"ADMINISTRADOR".equalsIgnoreCase(administrador.getRol().getNombre())) {
            throw new IllegalArgumentException("El usuario con ID " + administrador.getUid() + " no tiene rol de ADMINISTRADOR.");
        }

        cultivoExistente.setAdministrador(administrador);
    }

    // Guardar el cultivo actualizado
    return cultivoService.editarCultivo(cultivoExistente);
}


    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        cultivoService.eliminarCultivo(id);
    }
}
