package com.ceiba.tipodocumento.comando.manejador;

import org.springframework.stereotype.Component;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.tipodocumento.comando.ComandoTipoDocumento;
import com.ceiba.tipodocumento.comando.fabrica.FabricaTipoDocumento;
import com.ceiba.tipodocumento.modelo.entidad.TipoDocumento;
import com.ceiba.tipodocumento.servicio.ServicioActualizarTipoDocumento;

@Component
public class ManejadorActualizarTipoDocumento implements ManejadorComando<ComandoTipoDocumento> {

    private final FabricaTipoDocumento fabricaTipoDocumento;
    private final ServicioActualizarTipoDocumento servicioActualizarTipoDocumento;

    public ManejadorActualizarTipoDocumento(FabricaTipoDocumento fabricaTipoDocumento,
            ServicioActualizarTipoDocumento servicioActualizarTipoDocumento) {
        this.fabricaTipoDocumento = fabricaTipoDocumento;
        this.servicioActualizarTipoDocumento = servicioActualizarTipoDocumento;
    }

    public void ejecutar(ComandoTipoDocumento comandoTipoDocumento) {
        TipoDocumento tipoDocumento = this.fabricaTipoDocumento.crear(comandoTipoDocumento);
        this.servicioActualizarTipoDocumento.ejecutar(tipoDocumento);
    }
}
