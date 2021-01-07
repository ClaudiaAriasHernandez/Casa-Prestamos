package com.ceiba.tipodocumento.modelo.entidad;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

import lombok.Getter;

@Getter
public class TipoDocumento {

    private static final String SE_DEBE_INGRESAR_EL_TIPO_DE_DOCUMENTO = "Se debe ingresar el tipo de documento";
    private static final String SE_DEBE_INGRESAR_LA_DECRIPCION_DEL_DOCUMENTO = "Se debe ingresar la descripcion del tipo de documento";

    private Long id;
    private String tipoIdentificacion;
    private String descripcion;

    public TipoDocumento(Long id, String tipoIdentificacion, String descripcion) {
        validarObligatorio(tipoIdentificacion, SE_DEBE_INGRESAR_EL_TIPO_DE_DOCUMENTO);
        validarObligatorio(descripcion, SE_DEBE_INGRESAR_LA_DECRIPCION_DEL_DOCUMENTO);

        this.id = id;
        this.tipoIdentificacion = tipoIdentificacion;
        this.descripcion = descripcion;

    }

}
