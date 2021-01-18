package com.ceiba.tipodocumento.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.tipodocumento.modelo.dto.DtoTipoDocumento;
import com.ceiba.tipodocumento.puerto.dao.DaoTipoDocumento;

public class ServicioConsultarTipoDocumento {

    private static final String EL_TIPO_DE_DOCUMENTO_NO_EXISTE_EN_EL_SISTEMA = "El tipo de documento no existe en el sistema";

    private final DaoTipoDocumento daoTipoDocumento;

    public ServicioConsultarTipoDocumento(DaoTipoDocumento daoTipoDocumento) {
        this.daoTipoDocumento = daoTipoDocumento;
    }

    public DtoTipoDocumento ejecutar(String tipoIdentificacion) {

        DtoTipoDocumento datosTipoDocumento = this.daoTipoDocumento.consultarTipoDocumento(tipoIdentificacion);

        if (datosTipoDocumento == null) {
            throw new ExcepcionDuplicidad(EL_TIPO_DE_DOCUMENTO_NO_EXISTE_EN_EL_SISTEMA);

        } else {
            return datosTipoDocumento;
        }
    }

}
