package com.innovatexts.myFarm.controller;

import com.innovatexts.myFarm.DTO.CompraInsumoDTO;
import com.innovatexts.myFarm.models.CompraInsumo;
import com.innovatexts.myFarm.services.CompraInsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras-insumos")
@CrossOrigin(origins = "*")
public class CompraInsumoController {

    @Autowired
    private CompraInsumoService compraInsumoService;

    @PostMapping
    public CompraInsumo crearCompra(@RequestBody CompraInsumoDTO dto) {
        return compraInsumoService.registrarCompra(dto);
    }

    @GetMapping
    public List<CompraInsumo> listarCompras() {
        return compraInsumoService.listarCompras();
    }

    @GetMapping("/{id}")
    public CompraInsumo obtenerPorId(@PathVariable Integer id) {
        return compraInsumoService.obtenerCompraPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Compra de insumo no encontrada con ID: " + id));
    }

    @PutMapping("/{id}")
    public CompraInsumo actualizarCompra(@PathVariable Integer id, @RequestBody CompraInsumoDTO dto) {
    return compraInsumoService.actualizarCompra(id, dto);
}

    @DeleteMapping("/{id}")
    public void eliminarCompra(@PathVariable Integer id) {
        compraInsumoService.eliminarCompra(id);
    }
}