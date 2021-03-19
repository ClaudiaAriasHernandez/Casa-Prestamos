package com.ceiba.tipodocumento.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.tipodocumento.comando.ComandoTipoDocumento;
import com.ceiba.tipodocumento.comando.manejador.ManejadorActualizarTipoDocumento;
import com.ceiba.tipodocumento.comando.manejador.ManejadorCrearTipoDocumento;
import com.ceiba.tipodocumento.comando.manejador.ManejadorEliminarTipoDocumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/tipodocumentos")
@CrossOrigin(origins = "*")
@Api(tags = { "Controlador comando tipodocumento"})
public class ComandoControladorTipoDocumento {

    private final ManejadorCrearTipoDocumento manejadorCrearTipoDocumento;
	private final ManejadorEliminarTipoDocumento manejadorEliminarTipoDocumento;
	private final ManejadorActualizarTipoDocumento manejadorActualizarTipoDocumento;

    @Autowired
    public ComandoControladorTipoDocumento(ManejadorCrearTipoDocumento manejadorCrearTipoDocumento,
									 ManejadorEliminarTipoDocumento manejadorEliminarTipoDocumento,
									 ManejadorActualizarTipoDocumento manejadorActualizarTipoDocumento) {
        this.manejadorCrearTipoDocumento = manejadorCrearTipoDocumento;
		this.manejadorEliminarTipoDocumento = manejadorEliminarTipoDocumento;
		this.manejadorActualizarTipoDocumento = manejadorActualizarTipoDocumento;
    }

    @PostMapping
    @ApiOperation("Crear Tipodocumento")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoTipoDocumento comandoTipoDocumento) {
        return manejadorCrearTipoDocumento.ejecutar(comandoTipoDocumento);
    }

    @DeleteMapping(value="/{id}")
	@ApiOperation("Eliminar Tipodocumento")
	public void eliminar(@PathVariable Long id) {
		manejadorEliminarTipoDocumento.ejecutar(id);
	}

	@PutMapping(value="/{id}")
	@ApiOperation("Actualizar Tipodocumento")
	public void actualizar(@RequestBody ComandoTipoDocumento comandoTipoDocumento,@PathVariable Long id) {
		comandoTipoDocumento.setId(id);
		manejadorActualizarTipoDocumento.ejecutar(comandoTipoDocumento);
	}
}
