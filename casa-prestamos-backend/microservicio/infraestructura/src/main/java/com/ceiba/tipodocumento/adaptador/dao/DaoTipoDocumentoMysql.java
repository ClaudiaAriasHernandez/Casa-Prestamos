package com.ceiba.tipodocumento.adaptador.dao;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.tipodocumento.modelo.dto.DtoTipoDocumento;
import com.ceiba.tipodocumento.puerto.dao.DaoTipoDocumento;

@Component
public class DaoTipoDocumentoMysql implements DaoTipoDocumento {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "tipodocumento", value = "listar")
    private static String sqlListar;

    @SqlStatement(namespace = "tipodocumento", value = "consultarTipoDocumento")
    private static String sqlConsultarTipoDocumento;

    public DaoTipoDocumentoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoTipoDocumento> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar,
                new MapeoTipoDocumento());
    }

    @Override
    public DtoTipoDocumento consultarTipoDocumento(String tipoIdentificacion) {
        DtoTipoDocumento datosTipoDocumento = null;
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("tipoIdentificacion", tipoIdentificacion);

        List<DtoTipoDocumento> tipoDocumento = this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlConsultarTipoDocumento, paramSource, new MapeoTipoDocumento());

        for (DtoTipoDocumento tipoDocumentoInfo : tipoDocumento) {
            datosTipoDocumento = tipoDocumentoInfo;
        }
        return datosTipoDocumento;
    }
}
