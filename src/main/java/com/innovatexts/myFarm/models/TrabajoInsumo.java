package com.innovatexts.myFarm.models;

import jakarta.persistence.*;

@Entity
@Table(name = "trabajo_insumo")
public class TrabajoInsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer cantidadUsada;

    @ManyToOne
    @JoinColumn(name = "id_trabajo")
    private Trabajo trabajo;

    @ManyToOne
    @JoinColumn(name = "id_insumo")
    private Insumo insumo;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidadUsada() {
        return this.cantidadUsada;
    }

    public void setCantidadUsada(Integer cantidadUsada) {
        this.cantidadUsada = cantidadUsada;
    }

    public Insumo getInsumo() {
        return this.insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

        public Trabajo getTrabajo() {
        return this.trabajo;
    }

    public void setTrabajo(Trabajo trabajo) {
        this.trabajo = trabajo;
    }

}

