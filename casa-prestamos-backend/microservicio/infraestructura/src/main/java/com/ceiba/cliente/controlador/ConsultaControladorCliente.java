package com.ceiba.cliente.controlador;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.cliente.consulta.ManejadorListarClientes;
import com.ceiba.cliente.modelo.dto.DtoCliente;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/clientes")
@Api(tags = { "Controlador consulta cliente" })
public class ConsultaControladorCliente {

    private final ManejadorListarClientes manejadorListarClientes;

    public ConsultaControladorCliente(ManejadorListarClientes manejadorListarClientes) {
        this.manejadorListarClientes = manejadorListarClientes;
    }

    @GetMapping
    @ApiOperation("Listar Clientes")
    public List<DtoCliente> listar() {
        return this.manejadorListarClientes.ejecutar();
    }

}
