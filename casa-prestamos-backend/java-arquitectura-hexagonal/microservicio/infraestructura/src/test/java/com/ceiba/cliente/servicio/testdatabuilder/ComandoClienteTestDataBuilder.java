package com.ceiba.cliente.servicio.testdatabuilder;

import com.ceiba.cliente.comando.ComandoCliente;

public class ComandoClienteTestDataBuilder {

    private Long id;
    private String nombre;
    private String direccion;
    private String numeroDocumento;
    private String correo;
    private String telefono;
    private Long idTipoDocumento;

    public ComandoClienteTestDataBuilder() {
       
        nombre = "Katia Arismendy";
        direccion = "Call 62";
        numeroDocumento = "1037641017";
        correo = "kati@gmail.com";
        telefono = "5982222";
        idTipoDocumento = 1L;
    }

    public ComandoClienteTestDataBuilder conNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
        return this;
    }

    public ComandoClienteTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public ComandoCliente build() {
        return new ComandoCliente(id, nombre, direccion, numeroDocumento, correo, telefono, idTipoDocumento);
    }

}
