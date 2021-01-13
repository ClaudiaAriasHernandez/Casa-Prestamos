package com.ceiba.prestamo.consulta;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ceiba.prestamo.modelo.dto.DtoPrestamo;
import com.ceiba.prestamo.puerto.dao.DaoPrestamo;

@Component
public class ManejadorListarPrestamos {

    private final DaoPrestamo daoPrestamo;

    public ManejadorListarPrestamos(DaoPrestamo daoPrestamo) {
        this.daoPrestamo = daoPrestamo;

    }

    public List<DtoPrestamo> ejecutar() {
        return this.daoPrestamo.listar();
    }

}
