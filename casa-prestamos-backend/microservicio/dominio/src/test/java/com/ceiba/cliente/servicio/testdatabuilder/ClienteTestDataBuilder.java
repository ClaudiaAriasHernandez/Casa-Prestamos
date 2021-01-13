package com.ceiba.cliente.servicio.testdatabuilder;

import com.ceiba.cliente.modelo.entidad.Cliente;

public class ClienteTestDataBuilder {

    private Long id;
    private String nombre;
    private String direccion;
    private String numeroDocumento;
    private String correo;
    private String telefono;
    private Long idTipoDocumento;

    public ClienteTestDataBuilder() {

        id = 1L;
        nombre = "Katia Arismendy";
        direccion = "Call 62";
        numeroDocumento = "1037641017";
        correo = "kati@gmail.com";
        telefono = "5982222";
        idTipoDocumento = 1L;
    }

    public ClienteTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public ClienteTestDataBuilder conIdtipoDocumento(Long idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
        return this;
    }

    public ClienteTestDataBuilder conTelefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public ClienteTestDataBuilder conNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
        return this;
    }

    public ClienteTestDataBuilder conDireccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public ClienteTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public Cliente build() {
        return new Cliente(id, nombre, direccion, numeroDocumento, correo, telefono, idTipoDocumento);
    }
}
