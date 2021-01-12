package com.ceiba.cliente.modelo.entidad;

import static com.ceiba.dominio.ValidadorArgumento.validarLongitud;
import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

import lombok.Getter;

@Getter
public class Cliente {

    private static final String SE_DEBE_INGRESAR_EL_NOMBRE_CLIENTE = "Se debe ingresar el nombre del cliente";
    private static final String EL_TELEFONO_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A = "El número de telefono debe tener una longitud mayor o igual a %s";
    private static final String SE_DEBE_INGRESAR_EL_TELEFONO = "Se debe ingresar el número de telefono del cliente";
    private static final String SE_DEBE_INGRESAR_EL_NUMERO_DE_DOCUMENTO_DEL_CLIENTE = "Se debe ingresar el número de documento del cliente";
    private static final String SE_DEBE_INGRESAR_LA_DIRECCION_DEL_CLIENTE = "Se debe ingresar la dirección del cliente";
    private static final String SE_DEBE_INGRESAR_EL_TIPO_DE_DOCUMENTO_CLIENTE = "Se debe ingresar el tipo de documento del cliente";

    private static final int LONGITUD_MINIMA_TELEFONO = 7;

    private Long id;
    private String nombre;
    private String direccion;
    private String numeroDocumento;
    private String correo;
    private String telefono;
    private Long idTipoDocumento;

    public Cliente(Long id, String nombre, String direccion, String numeroDocumento, String correo, String telefono,
            Long idTipoDocumento) {
        validarObligatorio(nombre, SE_DEBE_INGRESAR_EL_NOMBRE_CLIENTE);
        validarObligatorio(telefono, SE_DEBE_INGRESAR_EL_TELEFONO);
        validarObligatorio(numeroDocumento, SE_DEBE_INGRESAR_EL_NUMERO_DE_DOCUMENTO_DEL_CLIENTE);
        validarObligatorio(direccion, SE_DEBE_INGRESAR_LA_DIRECCION_DEL_CLIENTE);
        validarLongitud(telefono, LONGITUD_MINIMA_TELEFONO,
                String.format(EL_TELEFONO_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A, LONGITUD_MINIMA_TELEFONO));
        validarObligatorio(idTipoDocumento, SE_DEBE_INGRESAR_EL_TIPO_DE_DOCUMENTO_CLIENTE);

        this.id = id;
        this.nombre = nombre;

        this.direccion = direccion;
        this.numeroDocumento = numeroDocumento;
        this.correo = correo;
        this.telefono = telefono;
        this.idTipoDocumento = idTipoDocumento;
    }
}
