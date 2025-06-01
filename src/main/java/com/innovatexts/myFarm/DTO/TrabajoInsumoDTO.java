package com.innovatexts.myFarm.DTO;

public class TrabajoInsumoDTO {
    private Integer id; // para editar o eliminar
    private Integer trabajoId;
    private Integer insumoId;
    private Integer cantidadUsada;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTrabajoId() {
        return this.trabajoId;
    }

    public void setTrabajoId(Integer trabajoId) {
        this.trabajoId = trabajoId;
    }

    public Integer getInsumoId() {
        return this.insumoId;
    }

    public void setInsumoId(Integer insumoId) {
        this.insumoId = insumoId;
    }

    public Integer getCantidadUsada() {
        return this.cantidadUsada;
    }

    public void setCantidadUsada(Integer cantidadUsada) {
        this.cantidadUsada = cantidadUsada;
    }

}
