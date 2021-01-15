package com.ceiba.prestamo.puerto.dao;

import java.util.List;

import com.ceiba.prestamo.modelo.dto.DtoPrestamo;

public interface DaoPrestamo {

    /**
     * Permite listar los prestamos
     * 
     * @return los prestamos
     */
    List<DtoPrestamo> listar();

    /**
     * Permite listar los prestamos por cliente
     * 
     * @param prestamo
     * 
     * @return los prestamos
     */
    DtoPrestamo listarPorIdCliente(Long id);
}
