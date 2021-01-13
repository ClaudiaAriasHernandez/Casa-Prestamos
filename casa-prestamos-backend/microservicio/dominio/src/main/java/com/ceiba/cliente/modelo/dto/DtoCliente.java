package com.ceiba.cliente.modelo.dto;

import com.ceiba.tipodocumento.modelo.dto.DtoTipoDocumento;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoCliente {

    private Long id;
    private String nombre;
    private String direccion;
    private String numeroDocumento;
    private String correo;
    private String telefono; 
    private Long idTipoDocumento;
    private DtoTipoDocumento dtoTipoDocumento;

}
