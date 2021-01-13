package com.ceiba.tipodocumento.comando.manejador;

import org.springframework.stereotype.Component;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.tipodocumento.comando.ComandoTipoDocumento;
import com.ceiba.tipodocumento.comando.fabrica.FabricaTipoDocumento;
import com.ceiba.tipodocumento.modelo.entidad.TipoDocumento;
import com.ceiba.tipodocumento.servicio.ServicioCrearTipoDocumento;

@Component
public class ManejadorCrearTipoDocumento
        implements ManejadorComandoRespuesta<ComandoTipoDocumento, ComandoRespuesta<Long>> {

    private final FabricaTipoDocumento fabricaTipoDocumento;
    private final ServicioCrearTipoDocumento servicioCrearTipoDocumento;

    public ManejadorCrearTipoDocumento(FabricaTipoDocumento fabricaTipoDocumento,
            ServicioCrearTipoDocumento servicioCrearTipoDocumento) {
        this.fabricaTipoDocumento = fabricaTipoDocumento;
        this.servicioCrearTipoDocumento = servicioCrearTipoDocumento;
    }

    public ComandoRespuesta<Long> ejecutar(ComandoTipoDocumento comandoTipoDocumento) {
        TipoDocumento tipoDocumento = this.fabricaTipoDocumento.crear(comandoTipoDocumento);
        return new ComandoRespuesta<>(this.servicioCrearTipoDocumento.ejecutar(tipoDocumento));
    }
}
