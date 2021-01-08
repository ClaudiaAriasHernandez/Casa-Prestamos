package com.ceiba.prestamo.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.prestamo.modelo.entidad.Prestamo;
import com.ceiba.prestamo.puerto.repositorio.RepositorioPrestamo;

public class ServicioActualizarPrestamo {

    private static final String EL_CLIENTE_NO_TIENE_UN_PRESTAMO_EN_EL_SISTEMA = "El cliente no tiene un prestamo en el sistema";

    private final RepositorioPrestamo repositorioPrestamo;

    public ServicioActualizarPrestamo(RepositorioPrestamo repositorioPrestamo) {
        this.repositorioPrestamo = repositorioPrestamo;

    }

    public void ejecutar(Prestamo prestamo) {

        validarExistenciaPrevia(prestamo);
        this.repositorioPrestamo.actualizar(prestamo);
    }

    private void validarExistenciaPrevia(Prestamo prestamo) {
        boolean existe = this.repositorioPrestamo.existeIdCliente(prestamo.getIdCliente());
        if (!existe) {
            throw new ExcepcionDuplicidad(EL_CLIENTE_NO_TIENE_UN_PRESTAMO_EN_EL_SISTEMA);
        }
    }

}
