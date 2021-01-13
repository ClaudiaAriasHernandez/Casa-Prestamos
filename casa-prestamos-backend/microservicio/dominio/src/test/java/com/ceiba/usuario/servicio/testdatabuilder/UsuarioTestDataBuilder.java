package com.ceiba.usuario.servicio.testdatabuilder;

import java.time.LocalDateTime;

import com.ceiba.usuario.modelo.entidad.Usuario;

public class UsuarioTestDataBuilder {

    private Long id;
    private String nombreUsuario;
    private String clave;
    private LocalDateTime fecha;

    public UsuarioTestDataBuilder() {
        nombreUsuario = "1234";
        clave = "1234";
        fecha = LocalDateTime.now();
    }

    public UsuarioTestDataBuilder conClave(String clave) {
        this.clave = clave;
        return this;
    }

    public UsuarioTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public UsuarioTestDataBuilder conNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
        return this;
    }

    public UsuarioTestDataBuilder conFecha(LocalDateTime fecha) {
        this.fecha = fecha;
        return this;
    }

    public Usuario build() {
        return new Usuario(id, nombreUsuario, clave, fecha);
    }
}
