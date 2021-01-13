package com.ceiba.prestamo.servicio.testdatabuilder;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.ceiba.prestamo.comando.ComandoPrestamo;

public class ComandoPrestamoTestDataBuilder {

    private Long id;
    private Date fechaSolicitud;
    private Date fechaEstimadaPago;
    private Date fechaPago;
    private Double valor;
    private double valorMora;
    private double valorInteres;
    private double valorRecargo;
    private double valorTotal;
    private String estado;
    private Long idCliente;

    public ComandoPrestamoTestDataBuilder() {
        id = 300L;
        fechaSolicitud = new GregorianCalendar(2021, Calendar.JANUARY, 07).getTime();
        fechaEstimadaPago = new GregorianCalendar(2021, Calendar.JANUARY, 21).getTime();
        fechaPago = null;
        valor = 1000000.0;
        valorMora = 0.0;
        valorInteres = 0.0;
        valorRecargo = 0.0;
        valorTotal = 0.0;
        estado = "D";
        idCliente = 1L;
    }

    public ComandoPrestamoTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public ComandoPrestamo build() {
        return new ComandoPrestamo(id, fechaSolicitud, fechaEstimadaPago, fechaPago, valor, valorMora, valorInteres,
                valorRecargo, valorTotal, estado, idCliente);
    }

}
