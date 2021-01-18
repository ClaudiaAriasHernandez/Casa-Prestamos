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
    
    

    /**
     * Permite consultar un tipo de documento por tipo de identificacion
     * 
     * @return informacion tipo documento
     */
    DtoTipoDocumento consultarTipoDocumento(String tipoIdentificacion);
}
