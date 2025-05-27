package com.innovatexts.myFarm.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.innovatexts.myFarm.DTO.CompraInsumoDTO;
import com.innovatexts.myFarm.models.CompraInsumo;
import com.innovatexts.myFarm.models.Insumo;
import com.innovatexts.myFarm.models.Proveedor;
import com.innovatexts.myFarm.repository.CompraInsumoRepository;
import com.innovatexts.myFarm.repository.InsumoRepository;
import com.innovatexts.myFarm.repository.ProveedorRepository;

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
        insumoRepository.save(insumo);

        return compraInsumoRepository.save(compra);
    }
}
