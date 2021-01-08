package com.ceiba.prestamo.servicio;

import static com.ceiba.dominio.fecha.OperacionesFecha.convertirFecha;
import static com.ceiba.dominio.fecha.OperacionesFecha.generarFechaActual;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.ceiba.cliente.puerto.repositorio.RepositorioCliente;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.prestamo.modelo.dto.DtoPrestamo;
import com.ceiba.prestamo.puerto.repositorio.RepositorioPrestamo;

public class ServicioConsultarPrestamo {

    private static final Long DIAS_PRESTAMO = 15L;
    private static final String EL_CLIENTE_NO_TIENE_UN_PRESTAMO_ACTIVO_EN_EL_SISTEMA = "El cliente no tiene un prestamo activo en el sistema";
    private static final String EL_CLIENTE_NO_EXISTE_EN_EL_SISTEMA = "El cliente no existe en el sistema";
    private static final double PORCENTAJE_INTERES = 3;
    private static final double PORCENTAJE_MORA = 1;
    private static final double PORCENTAJE_RECARGO = 2;

    private final RepositorioCliente repositorioCliente;

    private final RepositorioPrestamo repositorioPrestamo;

    public ServicioConsultarPrestamo(RepositorioCliente repositorioCliente, RepositorioPrestamo repositorioPrestamo) {
        this.repositorioCliente = repositorioCliente;

        this.repositorioPrestamo = repositorioPrestamo;
    }

    public List<DtoPrestamo> ejecutar(Long id) {
        long dias = 0;

        double valorRecargo;
        double valorInteres;
        double valorTotal;
        double valorMora;
        double valorTotalMora;
        Date fechaActual = convertirFecha(generarFechaActual());

        validarExistenciaPreviaCliente(id);
        validarExistenciaPreviaPrestamo(id);

        List<DtoPrestamo> prestamoCliente = this.repositorioPrestamo.listarPorIdCliente(id);
        for (DtoPrestamo dtoPrestamo : prestamoCliente) {
            System.err.println(dtoPrestamo.getFechaSolicitud());
            dtoPrestamo.setFechaPago(fechaActual);

            dias = generarDiasPrestamo(fechaActual, dtoPrestamo);

            boolean diaFecha = validarDiaFecha(convertToLocalDate(dtoPrestamo.getFechaSolicitud()));
            if (dias == DIAS_PRESTAMO || dias <= DIAS_PRESTAMO) {

                if (diaFecha) {
                    valorRecargo = (dtoPrestamo.getValor() / 100) * PORCENTAJE_RECARGO;
                    dtoPrestamo.setValorRecargo(valorRecargo);
                    valorInteres = (dtoPrestamo.getValor() / 100) * PORCENTAJE_INTERES;
                    dtoPrestamo.setValorInteres(valorInteres);
                    valorTotal = valorRecargo + valorInteres + dtoPrestamo.getValor();
                    dtoPrestamo.setValorTotal(valorTotal);
                } else {
                    valorInteres = (dtoPrestamo.getValor() / 100) * PORCENTAJE_INTERES;
                    dtoPrestamo.setValorInteres(valorInteres);
                    valorTotal = valorInteres + dtoPrestamo.getValor();
                    dtoPrestamo.setValorTotal(valorTotal);
                }
            } else {

                long diasMora = dias - DIAS_PRESTAMO;
                if (diaFecha) {
                    valorRecargo = (dtoPrestamo.getValor() / 100) * PORCENTAJE_RECARGO;
                    dtoPrestamo.setValorRecargo(valorRecargo);
                    valorInteres = (dtoPrestamo.getValor() / 100) * PORCENTAJE_INTERES;
                    dtoPrestamo.setValorInteres(valorInteres);
                    valorMora = (dtoPrestamo.getValor() / 100) * PORCENTAJE_MORA;
                    valorTotalMora = valorMora * diasMora;
                    dtoPrestamo.setValorMora(valorTotalMora);
                    valorTotal = valorRecargo + valorInteres + dtoPrestamo.getValor() + valorTotalMora;
                    dtoPrestamo.setValorTotal(valorTotal);
                } else {
                    valorInteres = (dtoPrestamo.getValor() / 100) * PORCENTAJE_INTERES;
                    dtoPrestamo.setValorInteres(valorInteres);
                    valorMora = (dtoPrestamo.getValor() / 100) * PORCENTAJE_MORA;
                    valorTotalMora = valorMora * diasMora;
                    dtoPrestamo.setValorMora(valorTotalMora);
                    valorTotal = valorInteres + dtoPrestamo.getValor() + valorTotalMora;
                    dtoPrestamo.setValorTotal(valorTotal);
                }
            }

        }

        return prestamoCliente;
    }

    private void validarExistenciaPreviaPrestamo(Long id) {
        boolean existe = this.repositorioPrestamo.existePrestamoActivo(id);
        if (!existe) {
            throw new ExcepcionDuplicidad(EL_CLIENTE_NO_TIENE_UN_PRESTAMO_ACTIVO_EN_EL_SISTEMA);
        }
    }

    private void validarExistenciaPreviaCliente(Long id) {
        boolean existe = this.repositorioCliente.existeId(id);

        if (!existe) {
            throw new ExcepcionDuplicidad(EL_CLIENTE_NO_EXISTE_EN_EL_SISTEMA);
        }

    }

    public boolean validarDiaFecha(LocalDate fechaSolicitud) {

        if ((fechaSolicitud.getDayOfWeek() == DayOfWeek.SATURDAY)
                || (fechaSolicitud.getDayOfWeek() == DayOfWeek.SUNDAY)) {

            return true;
        } else {
            return false;
        }

    }

    public LocalDate convertToLocalDate(Date fecha) {

        return Instant.ofEpochMilli(fecha.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private long generarDiasPrestamo(Date fechaActual, DtoPrestamo dtoPrestamo) {

        long calculoFecha;
        calculoFecha = (fechaActual.getTime() - dtoPrestamo.getFechaSolicitud().getTime());

        return TimeUnit.DAYS.convert(calculoFecha, TimeUnit.MILLISECONDS);
    }

}
