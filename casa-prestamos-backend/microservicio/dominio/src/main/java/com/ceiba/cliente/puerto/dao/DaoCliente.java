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


    /**
     * Permite consultar un cliente por tipo de documento y numero de documento
     * 
     * @return los clientes
     */
    DtoCliente consultarCliente(String tipoIdentificacion, String numeroDocumento);
}
