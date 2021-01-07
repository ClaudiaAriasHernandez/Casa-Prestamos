package com.ceiba.tipodocumento.puerto.dao;

import java.util.List;

import com.ceiba.tipodocumento.modelo.dto.DtoTipoDocumento;

public interface DaoTipoDocumento {

    /**
     * Permite listar tipos de documento
     * 
     * @return los tipos de documentos
     */
    List<DtoTipoDocumento> listar();
}
