package com.ceiba.cliente.comando;

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
    private String correo;
    private String telefono; 
    private Long idTipoDocumento;

}
