package com.ceiba.tipodocumento.servicio;

import com.ceiba.tipodocumento.puerto.repositorio.RepositorioTipoDocumento;

public class ServicioEliminarTipoDocumento {

    private final RepositorioTipoDocumento repositorioTipoDocumento;

    public ServicioEliminarTipoDocumento(RepositorioTipoDocumento repositorioTipoDocumento) {
        this.repositorioTipoDocumento = repositorioTipoDocumento;
    }

    public void ejecutar(Long id) {
        this.repositorioTipoDocumento.eliminar(id);
    }
}
