package com.innovatexts.myFarm.DTO;

import java.util.Date;


public class CultivoDTO {
    private String nombre;
    private String tipo;
    private String variedad;
    private Date fecha_siembra;
    private String hectareas;
    private String ubicacion;
    private Integer administrador;

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getVariedad() {
        return this.variedad;
    }

    public void setVariedad(String variedad) {
        this.variedad = variedad;
    }

    public Date getFecha_siembra() {
        return this.fecha_siembra;
    }

    public void setFecha_siembra(Date fecha_siembra) {
        this.fecha_siembra = fecha_siembra;
    }

    public String getHectareas() {
        return this.hectareas;
    }

    public void setHectareas(String hectareas) {
        this.hectareas = hectareas;
    }

    public String getUbicacion() {
        return this.ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getAdministrador() {
        return this.administrador;
    }

    public void setAdministrador(Integer administrador) {
        this.administrador = administrador;
    }

}
