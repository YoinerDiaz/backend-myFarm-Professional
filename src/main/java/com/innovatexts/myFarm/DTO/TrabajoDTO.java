package com.innovatexts.myFarm.DTO;

import java.util.Date;
import java.util.List;

public class TrabajoDTO {

    private String nombre;
    private String detalle;
    private Integer id_cultivo;
    private float inversion;
    private Date fecha_inicio;
    private Date fecha_fin;
    private List<Long> trabajadoresIds;

    public List<Long> getTrabajadoresIds() {
        return this.trabajadoresIds;
    }

    public void setTrabajadoresIds(List<Long> trabajadoresIds) {
        this.trabajadoresIds = trabajadoresIds;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalle() {
        return this.detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Integer getId_cultivo() {
        return this.id_cultivo;
    }

    public void setId_cultivo(Integer id_cultivo) {
        this.id_cultivo = id_cultivo;
    }

    public float getInversion() {
        return this.inversion;
    }

    public void setInversion(float inversion) {
        this.inversion = inversion;
    }

    public Date getFecha_inicio() {
        return this.fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return this.fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }


}
