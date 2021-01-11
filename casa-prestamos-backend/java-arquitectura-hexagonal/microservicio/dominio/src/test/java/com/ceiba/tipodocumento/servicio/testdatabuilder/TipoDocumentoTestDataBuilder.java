package com.ceiba.tipodocumento.servicio.testdatabuilder;

import com.ceiba.tipodocumento.modelo.entidad.TipoDocumento;

public class TipoDocumentoTestDataBuilder {

    private Long id;
    private String tipoIdentificacion;
    private String descripcion;

    public TipoDocumentoTestDataBuilder() {

        id = 1L;
        tipoIdentificacion = "CC";
        descripcion = "Cedúla de ciudadania";

    }

    public TipoDocumentoTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public TipoDocumentoTestDataBuilder conTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
        return this;
    }

    public TipoDocumentoTestDataBuilder conDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public TipoDocumento build() {
        return new TipoDocumento(id, tipoIdentificacion, descripcion);
    }
}
