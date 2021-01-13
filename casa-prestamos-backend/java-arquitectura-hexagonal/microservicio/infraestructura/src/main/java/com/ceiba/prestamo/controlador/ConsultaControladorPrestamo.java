package com.ceiba.prestamo.controlador;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.prestamo.consulta.ManejadorListarPrestamos;
import com.ceiba.prestamo.modelo.dto.DtoPrestamo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/prestamos")
@Api(tags = { "Controlador consulta prestamos" })
public class ConsultaControladorPrestamo {

    private final ManejadorListarPrestamos manejadorListarPrestamos;

    public ConsultaControladorPrestamo(ManejadorListarPrestamos manejadorListarPrestamos) {
        this.manejadorListarPrestamos = manejadorListarPrestamos;
    }

    @GetMapping
    @ApiOperation("Listar Prestamos")
    public List<DtoPrestamo> listar() {
        return this.manejadorListarPrestamos.ejecutar();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("Listar Prestamos Activos por cliente")
    public DtoPrestamo listarPorIdCliente(@PathVariable Long id) {

        return this.manejadorListarPrestamos.ejecutar(id);
    }

}
