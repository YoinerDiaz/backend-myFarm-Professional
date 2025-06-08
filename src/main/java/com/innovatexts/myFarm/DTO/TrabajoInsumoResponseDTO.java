package com.innovatexts.myFarm.DTO;

import com.innovatexts.myFarm.models.Insumo;
import com.innovatexts.myFarm.models.Trabajo;

public class TrabajoInsumoResponseDTO {

    private Integer id; // ID de la asignación
    private String trabajoNombre;
    private Integer trabajoId; // También puedes incluir el ID si es necesario
    private String insumoNombre;
    private Integer insumoId; // También puedes incluir el ID si es necesario
    private Integer cantidadUsada;

    // Constructor que toma el objeto de entidad TrabajoInsumo y mapea a DTO
    public TrabajoInsumoResponseDTO(Integer id, String trabajoNombre, Integer trabajoId, String insumoNombre, Integer insumoId, Integer cantidadUsada) {
        this.id = id;
        this.trabajoNombre = trabajoNombre;
        this.trabajoId = trabajoId;
        this.insumoNombre = insumoNombre;
        this.insumoId = insumoId;
        this.cantidadUsada = cantidadUsada;
    }

    // Constructor vacío (necesario para algunas librerías de mapeo o si deserializas)
    public TrabajoInsumoResponseDTO() {}

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrabajoNombre() {
        return trabajoNombre;
    }

    public void setTrabajoNombre(String trabajoNombre) {
        this.trabajoNombre = trabajoNombre;
    }

    public Integer getTrabajoId() {
        return trabajoId;
    }

    public void setTrabajoId(Integer trabajoId) {
        this.trabajoId = trabajoId;
    }

    public String getInsumoNombre() {
        return insumoNombre;
    }

    public void setInsumoNombre(String insumoNombre) {
        this.insumoNombre = insumoNombre;
    }

    public Integer getInsumoId() {
        return insumoId;
    }

    public void setInsumoId(Integer insumoId) {
        this.insumoId = insumoId;
    }

    public Integer getCantidadUsada() {
        return cantidadUsada;
    }

    public void setCantidadUsada(Integer cantidadUsada) {
        this.cantidadUsada = cantidadUsada;
    }
}