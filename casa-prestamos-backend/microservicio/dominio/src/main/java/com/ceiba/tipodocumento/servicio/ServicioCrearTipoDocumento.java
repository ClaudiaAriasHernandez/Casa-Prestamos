package com.ceiba.tipodocumento.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.tipodocumento.modelo.entidad.TipoDocumento;
import com.ceiba.tipodocumento.puerto.repositorio.RepositorioTipoDocumento;

public class ServicioCrearTipoDocumento {

    private static final String EL_TIPO_DE_DOCUMENTO_YA_EXISTE_EN_EL_SISTEMA = "El tipo de documento ya existe en el sistema";

    private final RepositorioTipoDocumento repositorioTipoDocumento;

    public ServicioCrearTipoDocumento(RepositorioTipoDocumento repositorioTipoDocumento) {
        this.repositorioTipoDocumento = repositorioTipoDocumento;
    }

    public Long ejecutar(TipoDocumento tipoDocumento) {
        validarExistenciaPrevia(tipoDocumento);
        return this.repositorioTipoDocumento.crear(tipoDocumento);
    }

    private void validarExistenciaPrevia(TipoDocumento tipoDocumento) {
        boolean existe = this.repositorioTipoDocumento.existe(tipoDocumento.getTipoIdentificacion());
        if (existe) {
            throw new ExcepcionDuplicidad(EL_TIPO_DE_DOCUMENTO_YA_EXISTE_EN_EL_SISTEMA);
        }
    }
}
