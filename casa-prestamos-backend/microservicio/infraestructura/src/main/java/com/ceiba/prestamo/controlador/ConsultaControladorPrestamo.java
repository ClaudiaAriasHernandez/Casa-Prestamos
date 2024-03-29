package com.ceiba.prestamo.controlador;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.prestamo.consulta.ManejadorListarPrestamoCliente;
import com.ceiba.prestamo.consulta.ManejadorListarPrestamos;
import com.ceiba.prestamo.modelo.dto.DtoPrestamo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/prestamos")
@CrossOrigin(origins = "*")
@Api(tags = { "Controlador consulta prestamos" })
public class ConsultaControladorPrestamo {

    private final ManejadorListarPrestamoCliente manejadorListarPrestamoCliente;
    private final ManejadorListarPrestamos manejadorListarPrestamos;

    public ConsultaControladorPrestamo(ManejadorListarPrestamos manejadorListarPrestamos,
            ManejadorListarPrestamoCliente manejadorListarPrestamoCliente) {
        this.manejadorListarPrestamos = manejadorListarPrestamos;
        this.manejadorListarPrestamoCliente = manejadorListarPrestamoCliente;
    }

    @GetMapping
    @ApiOperation("Listar Prestamos")
    public List<DtoPrestamo> listar() {
        return this.manejadorListarPrestamos.ejecutar();
    }

    @GetMapping(value = "/tipoidentificacion/{tipoidentificacion}/numerodocumento/{numerodocumento}")
    @ApiOperation("Listar Prestamos Activos por cliente")
    public DtoPrestamo listarPorIdCliente(@PathVariable("tipoidentificacion") String tipoIdentificacion,
            @PathVariable("numerodocumento") String numeroDocumento) {

        return this.manejadorListarPrestamoCliente.ejecutar(tipoIdentificacion,numeroDocumento );
    }

}
