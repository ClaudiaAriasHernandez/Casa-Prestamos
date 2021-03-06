package com.ceiba.prestamo.servicio;

import static com.ceiba.dominio.fecha.OperacionesFecha.convertirFecha;
import static com.ceiba.dominio.fecha.OperacionesFecha.generarFechaActual;

import java.time.LocalDate;

import com.ceiba.cliente.puerto.repositorio.RepositorioCliente;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.prestamo.modelo.entidad.Prestamo;
import com.ceiba.prestamo.puerto.repositorio.RepositorioPrestamo;

public class ServicioCrearPrestamo {

    private static final String DEUDOR = "D";
    private static final int DIAS_PRESTAMO = 15;
    private static final String EL_CLIENTE_YA_TIENE_UN_PRESTAMO_EN_EL_SISTEMA = "El cliente ya tiene un prestamo en el sistema";
    private static final String EL_CLIENTE_NO_EXISTE_EN_EL_SISTEMA = "El cliente no existe en el sistema";

    private final RepositorioCliente repositorioCliente;
    private final RepositorioPrestamo repositorioPrestamo;

    public ServicioCrearPrestamo(RepositorioCliente repositorioCliente, RepositorioPrestamo repositorioPrestamo) {
        this.repositorioCliente = repositorioCliente;
        this.repositorioPrestamo = repositorioPrestamo;
    }

    public Long ejecutar(Prestamo prestamo) {
        LocalDate fechaActual = generarFechaActual();
        validarExistenciaPreviaCliente(prestamo);
        validarExistenciaPrevia(prestamo);
        prestamo.setEstado(DEUDOR);
        prestamo.setFechaSolicitud(convertirFecha(fechaActual));

        prestamo.setFechaEstimadaPago(convertirFecha(generarFechaEstimadaPago(fechaActual, DIAS_PRESTAMO)));
        return this.repositorioPrestamo.crear(prestamo);
    }

    private void validarExistenciaPrevia(Prestamo prestamo) {
        boolean existe = this.repositorioPrestamo.existePrestamoActivo(prestamo.getIdCliente());
        if (existe) {
            throw new ExcepcionDuplicidad(EL_CLIENTE_YA_TIENE_UN_PRESTAMO_EN_EL_SISTEMA);
        }
    }

    private void validarExistenciaPreviaCliente(Prestamo prestamo) {
        boolean existe = this.repositorioCliente.existeId(prestamo.getIdCliente());

        if (!existe) {
            throw new ExcepcionDuplicidad(EL_CLIENTE_NO_EXISTE_EN_EL_SISTEMA);
        }

    }

    private LocalDate generarFechaEstimadaPago(LocalDate fechaSolicitud, int diasPrestamo) {

        int diasHabiles = 0;
        LocalDate fechaFinPrestamo = fechaSolicitud.plusDays(-1);
        while (diasHabiles < diasPrestamo) {
            fechaFinPrestamo = fechaFinPrestamo.plusDays(1);
            ++diasHabiles;
        }

        return fechaFinPrestamo;
    }

}
