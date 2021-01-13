package com.ceiba.prestamo.servicio;

import com.ceiba.cliente.puerto.repositorio.RepositorioCliente;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.prestamo.modelo.entidad.Prestamo;
import com.ceiba.prestamo.puerto.repositorio.RepositorioPrestamo;

public class ServicioPagarPrestamo {

    private static final String EL_CLIENTE_NO_TIENE_UN_PRESTAMO_ACTIVO_EN_EL_SISTEMA = "El cliente no tiene un prestamo activo en el sistema";
    private static final String EL_CLIENTE_NO_EXISTE_EN_EL_SISTEMA = "El cliente no existe en el sistema";
    private final RepositorioPrestamo repositorioPrestamo;
    private static final String PAGADO = "P";

    private final RepositorioCliente repositorioCliente;

    public ServicioPagarPrestamo(RepositorioCliente repositorioCliente, RepositorioPrestamo repositorioPrestamo) {
        this.repositorioCliente = repositorioCliente;
        this.repositorioPrestamo = repositorioPrestamo;
    }

    public void ejecutar(Prestamo prestamo) {
        validarExistenciaPreviaCliente(prestamo);
        validarExistenciaPreviaPrestamo(prestamo);
        prestamo.setEstado(PAGADO);
        this.repositorioPrestamo.actualizar(prestamo);
    }

    private void validarExistenciaPreviaPrestamo(Prestamo prestamo) {
        boolean existe = this.repositorioPrestamo.existePrestamoActivo(prestamo.getIdCliente());
        if (!existe) {
            throw new ExcepcionDuplicidad(EL_CLIENTE_NO_TIENE_UN_PRESTAMO_ACTIVO_EN_EL_SISTEMA);
        }
    }

    private void validarExistenciaPreviaCliente(Prestamo prestamo) {
        boolean existe = this.repositorioCliente.existeId(prestamo.getIdCliente());

        if (!existe) {
            throw new ExcepcionDuplicidad(EL_CLIENTE_NO_EXISTE_EN_EL_SISTEMA);
        }

    }

}
