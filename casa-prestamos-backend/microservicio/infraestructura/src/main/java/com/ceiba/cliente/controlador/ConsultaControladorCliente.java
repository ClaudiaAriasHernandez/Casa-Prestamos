package com.ceiba.cliente.controlador;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.cliente.consulta.ManejadorConsultarCliente;
import com.ceiba.cliente.consulta.ManejadorListarClientes;
import com.ceiba.cliente.modelo.dto.DtoCliente;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/clientes")
@Api(tags = { "Controlador consulta cliente" })
public class ConsultaControladorCliente {

    private final ManejadorListarClientes manejadorListarClientes;
    private final ManejadorConsultarCliente manejadorConsultarCliente;

    public ConsultaControladorCliente(ManejadorListarClientes manejadorListarClientes,
            ManejadorConsultarCliente manejadorConsultarCliente) {
        this.manejadorListarClientes = manejadorListarClientes;
        this.manejadorConsultarCliente = manejadorConsultarCliente;
    }

    @GetMapping
    @ApiOperation("Listar Clientes")
    public List<DtoCliente> listar() {
        return this.manejadorListarClientes.ejecutar();
    }

    @GetMapping(value = "/tipoidentificacion/{tipoidentificacion}/numerodocumento/{numerodocumento}")
    @ApiOperation("Consultar cliente")
    public DtoCliente consultarCliente(@PathVariable("tipoidentificacion") String tipoIdentificacion,
            @PathVariable("numerodocumento") String numeroDocumento) {

        return manejadorConsultarCliente.ejecutar(tipoIdentificacion, numeroDocumento);
    }

}
