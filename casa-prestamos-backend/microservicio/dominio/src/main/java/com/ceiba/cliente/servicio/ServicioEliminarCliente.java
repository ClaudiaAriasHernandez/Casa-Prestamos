package com.ceiba.cliente.servicio;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.ceiba.cliente.puerto.repositorio.RepositorioCliente;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionEntidadRelacionada;

public class ServicioEliminarCliente {
    private static final Logger LOGGER = Logger.getLogger(ServicioEliminarCliente.class.getName());

    private static final String EL_CLIENTE_NO_EXISTE_EN_EL_SISTEMA = "El cliente no existe en el sistema";
    private static final String NO_SE_PUEDE_ELIMINAR_EL_CLIENTE_DEBIDO_A_QUE_ESTA_LIGADO_A_UN_PRESTAMO = "No se puede eliminar el cliente debido a que esta ligado a un prestamo";

    private final RepositorioCliente repositorioCliente;

    public ServicioEliminarCliente(RepositorioCliente repositorioCliente) {
        this.repositorioCliente = repositorioCliente;
    }

    public void ejecutar(Long id) {
        validarExistenciaPrevia(id);

        try {
            this.repositorioCliente.eliminar(id);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, NO_SE_PUEDE_ELIMINAR_EL_CLIENTE_DEBIDO_A_QUE_ESTA_LIGADO_A_UN_PRESTAMO, e);
            throw new ExcepcionEntidadRelacionada(
                    NO_SE_PUEDE_ELIMINAR_EL_CLIENTE_DEBIDO_A_QUE_ESTA_LIGADO_A_UN_PRESTAMO);

        }
    }

    private void validarExistenciaPrevia(Long id) {
        boolean existe = this.repositorioCliente.existeId(id);
        if (!existe) {
            throw new ExcepcionDuplicidad(EL_CLIENTE_NO_EXISTE_EN_EL_SISTEMA);
        }
    }

}
