package com.ceiba.prestamo.comando;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoPrestamo {

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
    private String numeroDocumento;
    private String tipoIdentificacion;
}
