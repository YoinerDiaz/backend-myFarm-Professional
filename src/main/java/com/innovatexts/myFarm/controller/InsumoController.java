package com.innovatexts.myFarm.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.innovatexts.myFarm.DTO.InsumoDTO;
import com.innovatexts.myFarm.models.Insumo;
import com.innovatexts.myFarm.services.InsumoService;

@RestController
@RequestMapping("/api/insumos")
@CrossOrigin(origins = "*")
public class InsumoController {
    @Autowired
    private InsumoService insumoService;

    @GetMapping
    public List<Insumo> listar() {
        return insumoService.listarInsumos();
    }

    @GetMapping("/{id}")
    public Optional<Insumo> obtenerPorId(@PathVariable Integer id) {
        return insumoService.obtenerInsumoPorId(id);
    }

    @PostMapping
    public Insumo crear(@RequestBody Insumo insumo) {
        return insumoService.guardarInsumo(insumo);
    }

    @PutMapping("/{id}")
    public Insumo actualizar(@PathVariable Integer id, @RequestBody InsumoDTO insumoActualizado) {

        Optional<Insumo> insumoOpcional = insumoService.obtenerInsumoPorId(id);
        Insumo insumoExistente = null;

        if (insumoOpcional.isPresent()) {
            insumoExistente = insumoOpcional.get();
        }

        if (insumoActualizado.getNombre() != null) {
            insumoExistente.setNombre(insumoActualizado.getNombre());
        }

        if (insumoActualizado.getStock() != null) {
            insumoExistente.setStock(insumoActualizado.getStock());
        }
        // Guardar el usuario actualizado
        return insumoService.guardarInsumo(insumoExistente);

    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        insumoService.eliminarInsumo(id);
    }
}
