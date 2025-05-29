package com.innovatexts.myFarm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovatexts.myFarm.DTO.CompraInsumoDTO;
import com.innovatexts.myFarm.models.CompraInsumo;
import com.innovatexts.myFarm.models.Insumo;
import com.innovatexts.myFarm.models.Proveedor;
import com.innovatexts.myFarm.repository.CompraInsumoRepository;
import com.innovatexts.myFarm.repository.InsumoRepository;
import com.innovatexts.myFarm.repository.ProveedorRepository;

@Service
public class CompraInsumoService {

    @Autowired
    private CompraInsumoRepository compraInsumoRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private InsumoRepository insumoRepository;
    
    public CompraInsumo registrarCompra(CompraInsumoDTO dto) {
        Proveedor proveedor = proveedorRepository.findById(dto.getProveedorId())
                                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        Insumo insumo = insumoRepository.findById(dto.getInsumoId())
                            .orElseThrow(() -> new RuntimeException("Insumo no encontrado"));

        CompraInsumo compra = new CompraInsumo();
        compra.setProveedor(proveedor);
        compra.setInsumo(insumo);
        compra.setCantidad(dto.getCantidad());
        compra.setPrecioUnitario(dto.getPrecioUnitario());
        compra.setFechaCompra(dto.getFechaCompra());
        compra.setTotal(dto.getCantidad() * dto.getPrecioUnitario());

        // Actualizar el stock del insumo
        insumo.setStock(insumo.getStock() + dto.getCantidad());
        System.out.println("ESTE ES EL STOCK QUE SE VA A PONER: "+insumo.getStock());
        insumoRepository.save(insumo);

        return compraInsumoRepository.save(compra);
    }

    public List<CompraInsumo> listarCompras() {
        return compraInsumoRepository.findAll();
    }

    public Optional<CompraInsumo> obtenerCompraPorId(Integer id) {
        return compraInsumoRepository.findById(id);
    }


    public void eliminarCompra(Integer id) {
        compraInsumoRepository.deleteById(id);
    }

public CompraInsumo actualizarCompra(Integer id, CompraInsumoDTO dto) {

    CompraInsumo compraExistente = compraInsumoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Compra de insumo no encontrada con ID: " + id));


        // Establecer nuevo insumo
        Insumo nuevoInsumo = insumoRepository.findById(dto.getInsumoId())
                .orElseThrow(() -> new IllegalArgumentException("Insumo no encontrado con ID: " + dto.getInsumoId()));
        compraExistente.setInsumo(nuevoInsumo);

        // Ajustar stock del nuevo insumo con nueva cantidad
        if (dto.getCantidad() != null) {
            nuevoInsumo.setStock(dto.getCantidad());
            insumoRepository.save(nuevoInsumo);
        }
    
    

    // Actualizar proveedor si cambia
    if (dto.getProveedorId() != null) {
        Proveedor proveedor = proveedorRepository.findById(dto.getProveedorId())
                .orElseThrow(() -> new IllegalArgumentException("Proveedor no encontrado con ID: " + dto.getProveedorId()));
        compraExistente.setProveedor(proveedor);
    }

    // Actualizar los demás campos
    if (dto.getCantidad() != null) compraExistente.setCantidad(dto.getCantidad());
    if (dto.getPrecioUnitario() != null) compraExistente.setPrecioUnitario(dto.getPrecioUnitario());
    if (dto.getFechaCompra() != null) compraExistente.setFechaCompra(dto.getFechaCompra());

    if (compraExistente.getCantidad() != null && compraExistente.getPrecioUnitario() != null) {
        compraExistente.setTotal(compraExistente.getCantidad() * compraExistente.getPrecioUnitario());
    }

    return compraInsumoRepository.save(compraExistente);
}
    
}

