package com.ceiba.tipodocumento.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoTipoDocumento {

    private Long id;
    private String tipoIdentificacion;
    private String descripcion;

}
