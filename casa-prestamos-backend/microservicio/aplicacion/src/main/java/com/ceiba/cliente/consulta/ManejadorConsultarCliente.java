package com.ceiba.cliente.consulta;

import org.springframework.stereotype.Component;

import com.ceiba.cliente.modelo.dto.DtoCliente;
import com.ceiba.cliente.servicio.ServicioConsultarCliente;

@Component
public class ManejadorConsultarCliente {

    private final ServicioConsultarCliente servicioConsultarCliente;

    public ManejadorConsultarCliente(ServicioConsultarCliente servicioConsultarCliente) {

        this.servicioConsultarCliente = servicioConsultarCliente;

    }

    public DtoCliente ejecutar(String tipoIdentificacion, String numeroDocumento) {

        return this.servicioConsultarCliente.ejecutar(tipoIdentificacion, numeroDocumento);
    }
}
