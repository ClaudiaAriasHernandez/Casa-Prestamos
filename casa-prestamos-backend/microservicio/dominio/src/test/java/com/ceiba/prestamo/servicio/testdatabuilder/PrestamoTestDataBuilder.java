package com.ceiba.prestamo.servicio.testdatabuilder;

import java.util.Date;

import com.ceiba.prestamo.modelo.entidad.Prestamo;

public class PrestamoTestDataBuilder {

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

    public PrestamoTestDataBuilder() {

        id = 1L;
        fechaSolicitud = new Date(2021 - 01 - 07);
        fechaEstimadaPago = new Date(2021 - 01 - 21);
        fechaPago = new Date();
        valor = 1000000.0;
        valorMora = 0.0;
        valorInteres = 30000.0;
        valorRecargo = 0.0;
        valorTotal = 1030000.0;
        estado = "D";
        idCliente = 1L;

    }

    public PrestamoTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public PrestamoTestDataBuilder conValor(Double valor) {
        this.valor = valor;
        return this;
    }

    public PrestamoTestDataBuilder conIdCliente(Long idCliente) {
        this.idCliente = idCliente;
        return this;
    }

    public Prestamo build() {
        return new Prestamo(id, fechaSolicitud, fechaEstimadaPago, fechaPago, valor, valorMora, valorInteres,
                valorRecargo, valorTotal, estado, idCliente);
    }
}
