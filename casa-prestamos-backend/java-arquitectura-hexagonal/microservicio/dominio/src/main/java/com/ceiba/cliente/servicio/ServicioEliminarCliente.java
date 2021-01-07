package com.ceiba.cliente.servicio;

import com.ceiba.cliente.puerto.repositorio.RepositorioCliente;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;

public class ServicioEliminarCliente {
    private static final String EL_CLIENTE_NO_EXISTE_EN_EL_SISTEMA = "El cliente no existe en el sistema";

    private final RepositorioCliente repositorioCliente;

    public ServicioEliminarCliente(RepositorioCliente repositorioCliente) {
        this.repositorioCliente = repositorioCliente;
    }

    public void ejecutar(Long id) {
        validarExistenciaPrevia(id);
        this.repositorioCliente.eliminar(id);
    }

    private void validarExistenciaPrevia(Long id) {
        boolean existe = this.repositorioCliente.existeId(id);
        if (!existe) {
            throw new ExcepcionDuplicidad(EL_CLIENTE_NO_EXISTE_EN_EL_SISTEMA);
        }
    }

}
