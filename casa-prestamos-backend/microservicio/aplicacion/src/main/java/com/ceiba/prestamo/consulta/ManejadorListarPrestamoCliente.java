package com.ceiba.prestamo.consulta;

import org.springframework.stereotype.Component;

import com.ceiba.prestamo.modelo.dto.DtoPrestamo;
import com.ceiba.prestamo.servicio.ServicioConsultarPrestamo;

@Component
public class ManejadorListarPrestamoCliente {

    private final ServicioConsultarPrestamo servicioConsultarPrestamo;

    public ManejadorListarPrestamoCliente(ServicioConsultarPrestamo servicioConsultarPrestamo) {

        this.servicioConsultarPrestamo = servicioConsultarPrestamo;

    }

    public DtoPrestamo ejecutar(Long id) {

        return this.servicioConsultarPrestamo.ejecutar(id);
    }
}
