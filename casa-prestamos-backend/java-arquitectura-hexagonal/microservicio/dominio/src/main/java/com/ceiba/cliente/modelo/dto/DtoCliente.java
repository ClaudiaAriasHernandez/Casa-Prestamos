package com.ceiba.cliente.modelo.dto;

import org.springframework.beans.factory.annotation.Autowired;

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
    private String telefono;
    private String correo;
    private String idTipoDocumento;

    
    @Autowired
    private DtoTipoDocumento dtoTipoDocumento;


    public DtoCliente(Long id, String nombre, String direccion, String numeroDocumento, String telefono, String correo,
            String idTipoDocumento) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.numeroDocumento = numeroDocumento;
        this.telefono = telefono;
        this.correo = correo;
        this.idTipoDocumento = idTipoDocumento;
    }
    
    
}
