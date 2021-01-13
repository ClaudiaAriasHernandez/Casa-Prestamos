package com.ceiba.cliente.servicio;

import com.ceiba.cliente.modelo.entidad.Cliente;
import com.ceiba.cliente.puerto.repositorio.RepositorioCliente;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.tipodocumento.puerto.repositorio.RepositorioTipoDocumento;

public class ServicioActualizarCliente {

    private static final String EL_CLIENTE_NO_EXISTE_EN_EL_SISTEMA = "El cliente no existe en el sistema";
    private static final String EL_TIPO_DE_DOCUMENTO_NO_EXISTE_EN_EL_SISTEMA = "El tipo de documento no existe en el sistema";

    private final RepositorioCliente repositorioCliente;
    private final RepositorioTipoDocumento repositorioTipoDocumento;

    public ServicioActualizarCliente(RepositorioCliente repositorioCliente,
            RepositorioTipoDocumento repositorioTipoDocumento) {
        this.repositorioCliente = repositorioCliente;
        this.repositorioTipoDocumento = repositorioTipoDocumento;
    }

    public void ejecutar(Cliente cliente) {

        validarExistenciaPreviaTipoDocumento(cliente);
        validarExistenciaPrevia(cliente);
        this.repositorioCliente.actualizar(cliente);
    }

    private void validarExistenciaPrevia(Cliente cliente) {
        boolean existe = this.repositorioCliente.existeExcluyendoId(cliente.getId(), cliente.getNumeroDocumento());
        boolean existeId = this.repositorioCliente.existeId(cliente.getId());
        if (!existe || !existeId) {
            throw new ExcepcionDuplicidad(EL_CLIENTE_NO_EXISTE_EN_EL_SISTEMA);
        }

    }

    private void validarExistenciaPreviaTipoDocumento(Cliente cliente) {
        boolean existe = this.repositorioTipoDocumento.existeId(cliente.getIdTipoDocumento());
        if (!existe) {
            throw new ExcepcionDuplicidad(EL_TIPO_DE_DOCUMENTO_NO_EXISTE_EN_EL_SISTEMA);
        }
    }
}
