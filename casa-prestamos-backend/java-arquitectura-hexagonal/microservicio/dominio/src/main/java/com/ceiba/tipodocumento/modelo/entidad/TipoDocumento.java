package com.ceiba.tipodocumento.modelo.entidad;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

import java.util.Collection;

import javax.persistence.OneToMany;

import com.ceiba.cliente.modelo.entidad.Cliente;

import lombok.Getter;

@Getter
public class TipoDocumento {

	private static final String SE_DEBE_INGRESAR_EL_TIPO_DE_DOCUMENTO = "Se debe ingresar el tipo de documento";
	private static final String SE_DEBE_INGRESAR_LA_DECRIPCION_DEL_DOCUMENTO = "Se debe ingresar la descripcion del tipo de documento";
	private static final String SE_DEBE_INGRESAR_EL_ESTADO = "Se debe ingresar el estado";

	private Long id;
	private String tipoIdentificacion;
	private String descripcion;
	private String estado;

	@OneToMany(mappedBy = "tipoDocumento")
	private Collection<Cliente> cliente;

	public TipoDocumento(Long id, String tipoIdentificacion, String descripcion, String estado) {
		validarObligatorio(tipoIdentificacion, SE_DEBE_INGRESAR_EL_TIPO_DE_DOCUMENTO);
		validarObligatorio(descripcion, SE_DEBE_INGRESAR_LA_DECRIPCION_DEL_DOCUMENTO);
		validarObligatorio(estado, SE_DEBE_INGRESAR_EL_ESTADO);
		this.id = id;
		this.tipoIdentificacion = tipoIdentificacion;
		this.descripcion = descripcion;
		this.estado = estado;

	}

}
