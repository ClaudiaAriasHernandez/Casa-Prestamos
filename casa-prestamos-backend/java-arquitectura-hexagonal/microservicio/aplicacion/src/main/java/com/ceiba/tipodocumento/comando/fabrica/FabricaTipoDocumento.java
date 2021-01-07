package com.ceiba.tipodocumento.comando.fabrica;

import org.springframework.stereotype.Component;

import com.ceiba.tipodocumento.comando.ComandoTipoDocumento;
import com.ceiba.tipodocumento.modelo.entidad.TipoDocumento;

@Component
public class FabricaTipoDocumento {

    public TipoDocumento crear(ComandoTipoDocumento comandoTipoDocumento) {
        return new TipoDocumento(comandoTipoDocumento.getId(), comandoTipoDocumento.getTipoIdentificacion(),
                comandoTipoDocumento.getDescripcion());
    }

}
