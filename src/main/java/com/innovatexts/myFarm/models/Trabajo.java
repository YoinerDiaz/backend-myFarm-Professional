package com.innovatexts.myFarm.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
@Entity
@Table(name = "trabajo")
public class Trabajo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String detalle;

    @ManyToOne
    @JoinColumn(name = "id_cultivo")
    private Cultivo cultivo;

    private float inversion;
    private Date fecha_inicio;
    private Date fecha_fin;

     @ManyToMany
    @JoinTable(
        name = "trabajo_usuario",
        joinColumns = @JoinColumn(name = "id_trabajo"),
        inverseJoinColumns = @JoinColumn(name = "id_usuario")
    )
     private List<Usuario> trabajadores = new ArrayList<>();

     public List<Usuario> getTrabajadores() {
         return this.trabajadores;
     }

     public void setTrabajadores(List<Usuario> trabajadores) {
         this.trabajadores = trabajadores;
     }

        public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Cultivo getCultivo() {
        return this.cultivo;
    }

    public void setCultivo(Cultivo cultivo) {
        this.cultivo = cultivo;
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
