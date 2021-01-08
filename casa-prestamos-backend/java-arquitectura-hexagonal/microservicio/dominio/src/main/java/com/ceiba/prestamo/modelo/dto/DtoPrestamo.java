package com.ceiba.prestamo.modelo.dto;

import java.util.Date;

import com.ceiba.cliente.modelo.dto.DtoCliente;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoPrestamo {

    private Long id;
    private Date fechaSolicitud;
    private Date fechaEstimadaPago;
    private Date fechaPago;
    private double valor;
    private double valorMora;
    private double valorInteres;
    private double valorRecargo;
    private double valorTotal;
    private String estado;
    private Long idCliente;
    private DtoCliente dtoCliente;

}
