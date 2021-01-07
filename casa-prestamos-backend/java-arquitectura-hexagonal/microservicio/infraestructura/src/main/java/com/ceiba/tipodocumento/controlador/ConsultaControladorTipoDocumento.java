package com.ceiba.tipodocumento.controlador;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.tipodocumento.consulta.ManejadorListarTipoDocumentos;
import com.ceiba.tipodocumento.modelo.dto.DtoTipoDocumento;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/tipodocumentos")
@Api(tags = { "Controlador consulta tipodocumento" })
public class ConsultaControladorTipoDocumento {

    private final ManejadorListarTipoDocumentos manejadorListarTipoDocumentos;

    public ConsultaControladorTipoDocumento(ManejadorListarTipoDocumentos manejadorListarTipoDocumentos) {
        this.manejadorListarTipoDocumentos = manejadorListarTipoDocumentos;
    }

    @GetMapping
    @ApiOperation("Listar Tipodocumentos")
    public List<DtoTipoDocumento> listar() {
        return this.manejadorListarTipoDocumentos.ejecutar();
    }

}
