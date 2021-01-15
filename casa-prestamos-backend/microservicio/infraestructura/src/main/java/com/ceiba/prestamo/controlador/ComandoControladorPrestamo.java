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
import com.ceiba.prestamo.comando.manejador.ManejadorCrearPrestamo;
import com.ceiba.prestamo.comando.manejador.ManejadorPagarPrestamo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/prestamos")
@Api(tags = { "Controlador comando prestamo" })
public class ComandoControladorPrestamo {

    private final ManejadorCrearPrestamo manejadorCrearPrestamo;

    private final ManejadorPagarPrestamo manejadorPagarPrestamo;

    @Autowired
    public ComandoControladorPrestamo(ManejadorCrearPrestamo manejadorCrearPrestamo,
            ManejadorPagarPrestamo manejadorPagarPrestamo) {
        this.manejadorCrearPrestamo = manejadorCrearPrestamo;

        this.manejadorPagarPrestamo = manejadorPagarPrestamo;
    }

    @PostMapping
    @ApiOperation("Crear Prestamo")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoPrestamo comandoPrestamo) {
        return manejadorCrearPrestamo.ejecutar(comandoPrestamo);
    }

    @PutMapping(value = "/tipoidentificacion/{tipoidentificacion}/numerodocumento/{numerodocumento}")
    @ApiOperation("Pagar Prestamo")
    public void pagar(@RequestBody ComandoPrestamo comandoPrestamo,
            @PathVariable("tipoidentificacion") String tipoIdentificacion,
            @PathVariable("numerodocumento") String numeroDocumento) {
        comandoPrestamo.setNumeroDocumento(numeroDocumento);
        comandoPrestamo.setTipoIdentificacion(tipoIdentificacion);
        manejadorPagarPrestamo.ejecutar(comandoPrestamo);
    }

}
