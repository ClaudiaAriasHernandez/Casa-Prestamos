package com.ceiba.prestamo.consulta;

import org.springframework.stereotype.Component;

import com.ceiba.cliente.modelo.dto.DtoCliente;
import com.ceiba.cliente.servicio.ServicioConsultarCliente;
import com.ceiba.prestamo.modelo.dto.DtoPrestamo;
import com.ceiba.prestamo.servicio.ServicioConsultarPrestamo;

@Component
public class ManejadorListarPrestamoCliente {

    private final ServicioConsultarPrestamo servicioConsultarPrestamo;
    private final ServicioConsultarCliente servicioConsultarCliente;

    public ManejadorListarPrestamoCliente(ServicioConsultarPrestamo servicioConsultarPrestamo,
            ServicioConsultarCliente servicioConsultarCliente) {

        this.servicioConsultarPrestamo = servicioConsultarPrestamo;
        this.servicioConsultarCliente = servicioConsultarCliente;

    }

    public DtoPrestamo ejecutar(String tipoIdentificacion, String numeroDocumento) {
        DtoCliente cliente = this.servicioConsultarCliente.ejecutar(tipoIdentificacion, numeroDocumento);
        return this.servicioConsultarPrestamo.ejecutar(cliente.getId());
    }

}
