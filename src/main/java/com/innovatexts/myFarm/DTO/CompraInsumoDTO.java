package com.innovatexts.myFarm.DTO;

import java.sql.Date;

public class CompraInsumoDTO {
    private Integer insumoId;
    private Integer proveedorId;
    private Integer cantidad;
    private Float precioUnitario;
    private Date fechaCompra;

    public Integer getInsumoId() {
        return this.insumoId;
    }

    public void setInsumoId(Integer insumoId) {
        this.insumoId = insumoId;
    }

    public Integer getProveedorId() {
        return this.proveedorId;
    }

    public void setProveedorId(Integer proveedorId) {
        this.proveedorId = proveedorId;
    }

    public Integer getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPrecioUnitario() {
        return this.precioUnitario;
    }

    public void setPrecioUnitario(Float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Date getFechaCompra() {
        return this.fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

}
