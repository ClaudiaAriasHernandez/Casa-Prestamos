package com.ceiba.prestamo.puerto.repositorio;

import java.util.List;

import com.ceiba.prestamo.modelo.dto.DtoPrestamo;
import com.ceiba.prestamo.modelo.entidad.Prestamo;

public interface RepositorioPrestamo {
    /**
     * Permite crear un prestamo
     * 
     * @param prestamo
     * @return el id generado
     */
    Long crear(Prestamo prestamo);

    /**
     * Permite actualizar un prestamo
     * 
     * @param prestamo
     */
    void actualizar(Prestamo prestamo);    

    /**
     * Permite validar si existe un prestamo con un idCliente
     * 
     * @param idCliente
     * @return si existe o no
     */
    boolean existeIdCliente(Long idCliente);    
    
    /**
     * Permite validar si existe un prestamo Activo con un idCliente
     * 
     * @param idCliente
     * @return si existe o no
     */
    boolean existePrestamoActivo(Long idCliente); 
    
    
    /**
     * Permite validar si existe un prestamo con un id
     * @param id
     * @return si existe o no
     */
    boolean existeId(Long id);  
    
    
    /**
     * Permite listar los prestamos por cliente
     * 
     * @param prestamo
     * 
     * @return los prestamos
     */
    List<DtoPrestamo> listarPorIdCliente(Long id);

}