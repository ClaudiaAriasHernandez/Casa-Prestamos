package com.ceiba.cliente.puerto.dao;

import java.util.List;

import com.ceiba.cliente.modelo.dto.DtoCliente;

public interface DaoCliente {

    /**
     * Permite listar loa clientes
     * 
     * @return los clientes
     */
    List<DtoCliente> listar();
}
