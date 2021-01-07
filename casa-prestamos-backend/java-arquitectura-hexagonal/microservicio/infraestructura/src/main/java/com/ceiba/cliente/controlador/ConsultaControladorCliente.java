package com.ceiba.cliente.controlador;

import java.util.List;

import com.ceiba.usuario.consulta.ManejadorListarUsuarios;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.usuario.modelo.dto.DtoUsuario;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/clientes")
@Api(tags={"Controlador consulta cliente"})
public class ConsultaControladorCliente {

    private final ManejadorListarUsuarios manejadorListarUsuarios;

    public ConsultaControladorCliente(ManejadorListarUsuarios manejadorListarUsuarios) {
        this.manejadorListarUsuarios = manejadorListarUsuarios;
    }

    @GetMapping
    @ApiOperation("Listar Clientes")
    public List<DtoUsuario> listar() {
        return this.manejadorListarUsuarios.ejecutar();
    }

}
