package com.ceiba.tipodocumento.servicio;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionEntidadRelacionada;
import com.ceiba.tipodocumento.puerto.repositorio.RepositorioTipoDocumento;

public class ServicioEliminarTipoDocumento {

    private static final Logger LOGGER = Logger.getLogger(ServicioEliminarTipoDocumento.class.getName());

    private static final String NO_SE_PUEDE_ELIMINAR_EL_TIPO_DE_DOCUMENTO_DEBIDO_A_QUE_ESTA_LIGADO_A_UN_CLIENTE = "No se puede eliminar el tipo de documento debido a que esta ligado a un cliente";

    private static final String EL_TIPO_DE_DOCUMENTO_NO_EXISTE_EN_EL_SISTEMA = "El tipo de documento no existe en el sistema";

    private final RepositorioTipoDocumento repositorioTipoDocumento;

    public ServicioEliminarTipoDocumento(RepositorioTipoDocumento repositorioTipoDocumento) {
        this.repositorioTipoDocumento = repositorioTipoDocumento;
    }

    public void ejecutar(Long id) {
        validarExistenciaPrevia(id);

        try {
            this.repositorioTipoDocumento.eliminar(id);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, NO_SE_PUEDE_ELIMINAR_EL_TIPO_DE_DOCUMENTO_DEBIDO_A_QUE_ESTA_LIGADO_A_UN_CLIENTE,
                    e);

            throw new ExcepcionEntidadRelacionada(
                    NO_SE_PUEDE_ELIMINAR_EL_TIPO_DE_DOCUMENTO_DEBIDO_A_QUE_ESTA_LIGADO_A_UN_CLIENTE);
        }

    }

    private void validarExistenciaPrevia(Long id) {
        boolean existe = this.repositorioTipoDocumento.existeId(id);
        if (!existe) {
            throw new ExcepcionDuplicidad(EL_TIPO_DE_DOCUMENTO_NO_EXISTE_EN_EL_SISTEMA);
        }
    }
}
