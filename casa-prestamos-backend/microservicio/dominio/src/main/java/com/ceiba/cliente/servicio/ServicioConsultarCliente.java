package com.ceiba.cliente.servicio;

import com.ceiba.cliente.modelo.dto.DtoCliente;
import com.ceiba.cliente.puerto.dao.DaoCliente;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;

public class ServicioConsultarCliente {

    private static final String EL_CLIENTE_NO_EXISTE_EN_EL_SISTEMA = "El cliente no existe en el sistema";

    private final DaoCliente daoCliente;

    public ServicioConsultarCliente(DaoCliente daoCliente) {
        this.daoCliente = daoCliente;
    }

    public DtoCliente ejecutar(String tipoIdentificacion, String numeroDocumento) {

        DtoCliente datosCliente = this.daoCliente.consultarCliente(tipoIdentificacion, numeroDocumento);

        if (datosCliente == null) {
            throw new ExcepcionDuplicidad(EL_CLIENTE_NO_EXISTE_EN_EL_SISTEMA);

        } else {
            return datosCliente;
        }
    }

}
