package com.innovatexts.myFarm.DTO;

public class UsuarioDTO {
    private String nombre;
    private String usuario;
    private String contacto;
    private Integer rol;

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContacto() {
        return this.contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public Integer getRol() {
        return this.rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
    }

}
