package com.innovatexts.myFarm.models;
import java.util.Date;

import jakarta.persistence.*;
@Entity
@Table(name = "cultivo")
public class Cultivo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String nombre;
    private String tipo;
    private String variedad;
    private Date fecha_siembra;
    private String hectareas;
    private String ubicacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_administrador", nullable = false)
    private Usuario administrador;

    public Usuario getAdministrador() {
        return this.administrador;
    }

    public void setAdministrador(Usuario administrador) {
        this.administrador = administrador;
    }


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


}