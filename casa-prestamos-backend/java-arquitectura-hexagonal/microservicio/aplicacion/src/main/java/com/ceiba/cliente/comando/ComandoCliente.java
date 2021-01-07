package com.ceiba.cliente.comando;

import org.springframework.beans.factory.annotation.Autowired;

import com.ceiba.tipodocumento.modelo.entidad.TipoDocumento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoCliente {

    private Long id;
    private String nombre;
    private String direccion;
    private String numeroDocumento;
    private String telefono;
    private String correo;
    private String idTipoDocumento;

    @Autowired
    private TipoDocumento tipoDocumento;
}
