package com.ceiba.tipodocumento.puerto.repositorio;

import com.ceiba.tipodocumento.modelo.entidad.TipoDocumento;

public interface RepositorioTipoDocumento {
    /**
     * Permite crear un TipoDocumento
     * @param Prestamo
     * @return el id generado
     */
    Long crear(TipoDocumento tipoDocumento);

    /**
     * Permite actualizar un TipoDocumento
     * @param Prestamo
     */
    void actualizar(TipoDocumento tipoDocumento);

    /**
     * Permite eliminar un TipoDocumento
     * @param id
     */
    void eliminar(Long id);

    /**
     * Permite validar si existe un TipoDocumento con un tipoIdentificacion
     * @param tipoIdentificacion
     * @return si existe o no
     */
    boolean existe(String tipoIdentificacion);

    /**
     * Permite validar si existe un TipoDocumento con un id
     * @param id
     * @return si existe o no
     */
    boolean existeId(Long id);
    
    /**
     * Permite validar si existe un TipoDocumento con un tipoIdentificacion 
     * @param tipoIdentificacion
     * @return si existe o no
     */
    boolean existeTipoIdentificacion(String tipoIdentificacion);

   

}
