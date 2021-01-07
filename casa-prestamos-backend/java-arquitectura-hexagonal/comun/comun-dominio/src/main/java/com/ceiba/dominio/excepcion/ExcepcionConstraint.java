package com.ceiba.dominio.excepcion;

public class ExcepcionConstraint extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExcepcionConstraint(String mensaje) {
        super(mensaje);
    }
}
