package com.ceiba.dominio.excepcion;

public class ExcepcionEntidadRelacionada extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExcepcionEntidadRelacionada(String mensaje) {
        super(mensaje);
    }
}
