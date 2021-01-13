package com.ceiba.tipodocumento.comando.manejador;

import org.springframework.stereotype.Component;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.tipodocumento.servicio.ServicioEliminarTipoDocumento;

@Component
public class ManejadorEliminarTipoDocumento implements ManejadorComando<Long> {

    private final ServicioEliminarTipoDocumento servicioEliminarTipoDocumento;

    public ManejadorEliminarTipoDocumento(ServicioEliminarTipoDocumento servicioEliminarTipoDocumento) {
        this.servicioEliminarTipoDocumento = servicioEliminarTipoDocumento;
    }

    public void ejecutar(Long idTipoDocumento) {
        this.servicioEliminarTipoDocumento.ejecutar(idTipoDocumento);
    }
}
