package com.ceiba.prestamo.consulta;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ceiba.prestamo.modelo.dto.DtoPrestamo;
import com.ceiba.prestamo.puerto.dao.DaoPrestamo;
import com.ceiba.prestamo.servicio.ServicioConsultarPrestamo;

@Component
public class ManejadorListarPrestamos {

    private final DaoPrestamo daoPrestamo;
    private final ServicioConsultarPrestamo servicioConsultarPrestamo;

    public ManejadorListarPrestamos(DaoPrestamo daoPrestamo, ServicioConsultarPrestamo servicioConsultarPrestamo) {
        this.daoPrestamo = daoPrestamo;
        this.servicioConsultarPrestamo = servicioConsultarPrestamo;

    }

    public List<DtoPrestamo> ejecutar() {
        return this.daoPrestamo.listar();
    }

    public List<DtoPrestamo> ejecutar(Long id) {

        return this.servicioConsultarPrestamo.ejecutar(id);
    }
}
