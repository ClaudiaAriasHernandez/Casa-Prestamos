package com.ceiba.tipodocumento.adaptador.repositorio;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.tipodocumento.modelo.entidad.TipoDocumento;
import com.ceiba.tipodocumento.puerto.repositorio.RepositorioTipoDocumento;

@Repository
public class RepositorioTipoDocumentoMysql implements RepositorioTipoDocumento {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "tipodocumento", value = "crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "tipodocumento", value = "actualizar")
    private static String sqlActualizar;

    @SqlStatement(namespace = "tipodocumento", value = "eliminar")
    private static String sqlEliminar;

    @SqlStatement(namespace = "tipodocumento", value = "existe")
    private static String sqlExiste;

    @SqlStatement(namespace = "tipodocumento", value = "existeExcluyendoId")
    private static String sqlExisteExcluyendoId;

    public RepositorioTipoDocumentoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(TipoDocumento tipoDocumento) {
        return this.customNamedParameterJdbcTemplate.crear(tipoDocumento, sqlCrear);
    }

    @Override
    public void eliminar(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, paramSource);
    }

    @Override
    public boolean existe(String tipoIdentificacion) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("tipoIdentificacion", tipoIdentificacion);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExiste,
                paramSource, Boolean.class);
    }

    @Override
    public void actualizar(TipoDocumento tipoDocumento) {
        this.customNamedParameterJdbcTemplate.actualizar(tipoDocumento, sqlActualizar);
    }

    @Override
    public boolean existeExcluyendoId(Long id, String tipoIdentificacion) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        paramSource.addValue("tipoIdentificacion", tipoIdentificacion);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlExisteExcluyendoId, paramSource, Boolean.class);
    }
}
