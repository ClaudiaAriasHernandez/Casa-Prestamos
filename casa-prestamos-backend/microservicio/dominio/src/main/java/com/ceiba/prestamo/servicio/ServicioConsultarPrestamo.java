package com.ceiba.prestamo.servicio;

import static com.ceiba.dominio.fecha.OperacionesFecha.convertirFecha;
import static com.ceiba.dominio.fecha.OperacionesFecha.generarFechaActual;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.ceiba.cliente.puerto.repositorio.RepositorioCliente;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.prestamo.modelo.dto.DtoPrestamo;
import com.ceiba.prestamo.puerto.dao.DaoPrestamo;
import com.ceiba.prestamo.puerto.repositorio.RepositorioPrestamo;

public class ServicioConsultarPrestamo {

    private static final int PORCENTAJE_DIVISION = 100;
    private static final Long DIAS_PRESTAMO = 15L;
    private static final String EL_CLIENTE_NO_TIENE_UN_PRESTAMO_ACTIVO_EN_EL_SISTEMA = "El cliente no tiene un prestamo activo en el sistema";
    private static final String EL_CLIENTE_NO_EXISTE_EN_EL_SISTEMA = "El cliente no existe en el sistema";
    private static final double PORCENTAJE_INTERES = 3;
    private static final double PORCENTAJE_MORA = 1;
    private static final double PORCENTAJE_RECARGO = 2;

    private final RepositorioCliente repositorioCliente;

    private final RepositorioPrestamo repositorioPrestamo;

    private final DaoPrestamo daoPrestamo;

    public ServicioConsultarPrestamo(RepositorioCliente repositorioCliente, RepositorioPrestamo repositorioPrestamo,
            DaoPrestamo daoPrestamo) {
        this.repositorioCliente = repositorioCliente;

        this.repositorioPrestamo = repositorioPrestamo;
        this.daoPrestamo = daoPrestamo;
    }

    public DtoPrestamo ejecutar(Long id) {
        long dias = 0;

        double valorTotal;

        Date fechaActual = convertirFecha(generarFechaActual());

        validarExistenciaPreviaCliente(id);
        validarExistenciaPreviaPrestamo(id);

        DtoPrestamo prestamoCliente = this.daoPrestamo.listarPorIdCliente(id);

        prestamoCliente.setFechaPago(fechaActual);

        dias = generarDiasPrestamo(fechaActual, prestamoCliente);

        boolean diaFecha = validarDiaFecha(convertToLocalDate(prestamoCliente.getFechaSolicitud()));
        if (dias == DIAS_PRESTAMO || dias <= DIAS_PRESTAMO) {

            if (diaFecha) {

                valorTotal = generarValorRecargo(prestamoCliente) + generarValorInteres(prestamoCliente)
                        + prestamoCliente.getValor();
                prestamoCliente.setValorTotal(valorTotal);
            } else {

                valorTotal = generarValorInteres(prestamoCliente) + prestamoCliente.getValor();
                prestamoCliente.setValorTotal(valorTotal);
            }

        } else {

            long diasMora = dias - DIAS_PRESTAMO;
            if (diaFecha) {

                valorTotal = generarValorRecargo(prestamoCliente) + generarValorInteres(prestamoCliente)
                        + prestamoCliente.getValor() + generarValorMora(prestamoCliente, diasMora);
                prestamoCliente.setValorTotal(valorTotal);
            } else {

                valorTotal = generarValorInteres(prestamoCliente) + prestamoCliente.getValor()
                        + generarValorMora(prestamoCliente, diasMora);
                prestamoCliente.setValorTotal(valorTotal);
            }
        }

        return prestamoCliente;
    }

    private double generarValorMora(DtoPrestamo prestamoCliente, long diasMora) {
        double valorMora;
        double valorTotalMora;
        valorMora = (prestamoCliente.getValor() / PORCENTAJE_DIVISION) * PORCENTAJE_MORA;
        valorTotalMora = valorMora * diasMora;
        prestamoCliente.setValorMora(valorTotalMora);
        return valorTotalMora;
    }

    private double generarValorInteres(DtoPrestamo prestamoCliente) {
        double valorInteres;
        valorInteres = (prestamoCliente.getValor() / PORCENTAJE_DIVISION) * PORCENTAJE_INTERES;
        prestamoCliente.setValorInteres(valorInteres);
        return valorInteres;
    }

    private double generarValorRecargo(DtoPrestamo prestamoCliente) {
        double valorRecargo;
        valorRecargo = (prestamoCliente.getValor() / PORCENTAJE_DIVISION) * PORCENTAJE_RECARGO;
        prestamoCliente.setValorRecargo(valorRecargo);
        return valorRecargo;
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
        return fechaSolicitud.getDayOfWeek() == DayOfWeek.SATURDAY || fechaSolicitud.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public LocalDate convertToLocalDate(Date fecha) {

        return Instant.ofEpochMilli(fecha.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private long generarDiasPrestamo(Date fechaActual, DtoPrestamo dtoPrestamo) {

        LocalDate inicio = Instant.ofEpochMilli(fechaActual.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

        LocalDate fin = Instant.ofEpochMilli(dtoPrestamo.getFechaSolicitud().getTime()).atZone(ZoneId.systemDefault())
                .toLocalDate();

        return Duration.between(fin.atStartOfDay(), inicio.atStartOfDay()).toDays() + 1;
    }

}
