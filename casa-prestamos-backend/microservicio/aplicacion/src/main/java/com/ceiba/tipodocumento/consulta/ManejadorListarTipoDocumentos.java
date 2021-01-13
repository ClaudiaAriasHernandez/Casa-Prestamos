package com.ceiba.tipodocumento.consulta;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ceiba.tipodocumento.modelo.dto.DtoTipoDocumento;
import com.ceiba.tipodocumento.puerto.dao.DaoTipoDocumento;

@Component
public class ManejadorListarTipoDocumentos {

    private final DaoTipoDocumento daoTipoDocumento;

    public ManejadorListarTipoDocumentos(DaoTipoDocumento daoTipoDocumento) {
        this.daoTipoDocumento = daoTipoDocumento;
    }

    public List<DtoTipoDocumento> ejecutar() {
        return this.daoTipoDocumento.listar();
    }
}
