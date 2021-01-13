package com.ceiba.prestamo.modelo.entidad;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Prestamo {

    private static final String SE_DEBE_INGRESAR_EL_USUARIO = "Se debe ingresar el usuario";
    private static final String SE_DEBE_INGRESAR_EL_VALOR_DEL_PRESTAMO = "Se debe ingresar el valor del prestamo";

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

    public Prestamo(Long id, Date fechaSolicitud, Date fechaEstimadaPago, Date fechaPago, Double valor,
            double valorMora, double valorInteres, double valorRecargo, double valorTotal, String estado,
            Long idCliente) {

        validarObligatorio(valor, SE_DEBE_INGRESAR_EL_VALOR_DEL_PRESTAMO);
        validarObligatorio(idCliente, SE_DEBE_INGRESAR_EL_USUARIO);

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
