package com.ceiba.tipodocumento.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.tipodocumento.modelo.entidad.TipoDocumento;
import com.ceiba.tipodocumento.puerto.repositorio.RepositorioTipoDocumento;

public class ServicioActualizarTipoDocumento {

    private static final String EL_TIPO_DE_DOCUMENTO_NO_EXISTE_EN_EL_SISTEMA = "El tipo de documento no existe en el sistema";

    private final RepositorioTipoDocumento repositorioTipoDocumento;

    public ServicioActualizarTipoDocumento(RepositorioTipoDocumento repositorioTipoDocumento) {
        this.repositorioTipoDocumento = repositorioTipoDocumento;
    }

    public void ejecutar(TipoDocumento tipoDocumento) {
        validarExistenciaPrevia(tipoDocumento);
        this.repositorioTipoDocumento.actualizar(tipoDocumento);
    }

    private void validarExistenciaPrevia(TipoDocumento tipoDocumento) {
        boolean existe = this.repositorioTipoDocumento.existeTipoIdentificacion(tipoDocumento.getTipoIdentificacion());
        boolean existeId = this.repositorioTipoDocumento.existeId(tipoDocumento.getId());
        if (!existe || !existeId) {
            throw new ExcepcionDuplicidad(EL_TIPO_DE_DOCUMENTO_NO_EXISTE_EN_EL_SISTEMA);
        }

    }
}
