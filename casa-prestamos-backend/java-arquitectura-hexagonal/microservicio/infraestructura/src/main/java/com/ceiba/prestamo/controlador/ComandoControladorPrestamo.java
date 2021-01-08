package com.ceiba.prestamo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.ComandoRespuesta;
import com.ceiba.prestamo.comando.ComandoPrestamo;
import com.ceiba.prestamo.comando.manejador.ManejadorActualizarPrestamo;
import com.ceiba.prestamo.comando.manejador.ManejadorCrearPrestamo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/prestamos")
@Api(tags = { "Controlador comando prestamo" })
public class ComandoControladorPrestamo {

    private final ManejadorCrearPrestamo manejadorCrearPrestamo;

    private final ManejadorActualizarPrestamo manejadorActualizarPrestamo;

    @Autowired
    public ComandoControladorPrestamo(ManejadorCrearPrestamo manejadorCrearPrestamo,
            ManejadorActualizarPrestamo manejadorActualizarPrestamo) {
        this.manejadorCrearPrestamo = manejadorCrearPrestamo;

        this.manejadorActualizarPrestamo = manejadorActualizarPrestamo;
    }

    @PostMapping
    @ApiOperation("Crear Prestamo")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoPrestamo comandoPrestamo) {
        return manejadorCrearPrestamo.ejecutar(comandoPrestamo);
    }

    @PutMapping(value = "/{id}")
    @ApiOperation("Actualizar Prestamo")
    public void actualizar(@RequestBody ComandoPrestamo comandoPrestamo, @PathVariable Long id) {
        comandoPrestamo.setId(id);
        manejadorActualizarPrestamo.ejecutar(comandoPrestamo);
    }
}
