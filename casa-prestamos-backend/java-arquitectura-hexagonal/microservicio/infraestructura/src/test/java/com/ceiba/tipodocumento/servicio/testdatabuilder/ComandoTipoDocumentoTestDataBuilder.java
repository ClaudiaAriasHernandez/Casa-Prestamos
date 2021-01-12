package com.ceiba.tipodocumento.servicio.testdatabuilder;

import com.ceiba.tipodocumento.comando.ComandoTipoDocumento;

public class ComandoTipoDocumentoTestDataBuilder {

    private Long id;
    private String tipoIdentificacion;
    private String descripcion;

    public ComandoTipoDocumentoTestDataBuilder() {
        id = 2L;
        tipoIdentificacion = "TI";
        descripcion = "Tarjeta de Identidad";
    }

    public ComandoTipoDocumentoTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public ComandoTipoDocumento build() {
        return new ComandoTipoDocumento(id, tipoIdentificacion, descripcion);
    }

}
