package com.ceiba.manejador;

import org.springframework.transaction.annotation.Transactional;

import com.ceiba.ComandoRespuesta;

public interface ManejadorComandoRespuesta<C, R> {

	@Transactional
	ComandoRespuesta<Long> ejecutar(C comando);
}