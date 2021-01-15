package com.ceiba.prestamo.modelo.entidad;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Prestamo {

    private static final String SE_DEBE_INGRESAR_EL_VALOR_DEL_PRESTAMO = "Se debe ingresar el valor del prestamo";

    private Long id;
    private Double valor;
    private Date fechaSolicitud;
    private Date fechaEstimadaPago;
    private Date fechaPago;
    private double valorMora;
    private double valorRecargo;
    private double valorInteres;
    private String estado;
    private double valorTotal;
    private Long idCliente;

    public Prestamo(Long id, Date fechaSolicitud, Date fechaEstimadaPago, Date fechaPago, Double valor,
            double valorMora, double valorInteres, double valorRecargo, double valorTotal, String estado,
            Long idCliente) {

        validarObligatorio(valor, SE_DEBE_INGRESAR_EL_VALOR_DEL_PRESTAMO);

        this.id = id;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaEstimadaPago = fechaEstimadaPago;
        this.fechaPago = fechaPago;
        this.valor = valor;
        this.valorMora = valorMora;
        this.valorInteres = valorInteres;
        this.valorRecargo = valorRecargo;
        this.valorTotal = valorTotal;
        this.estado = estado;
        this.idCliente = idCliente;

    }

}
