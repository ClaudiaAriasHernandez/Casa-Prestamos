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
    private static String sqlCrearTipoDocumento;

    @SqlStatement(namespace = "tipodocumento", value = "actualizar")
    private static String sqlActualizarTipoDocumento;

    @SqlStatement(namespace = "tipodocumento", value = "eliminar")
    private static String sqlEliminarTipoDocumento;

    @SqlStatement(namespace = "tipodocumento", value = "existe")
    private static String sqlExisteTipoDocumento;

    @SqlStatement(namespace = "tipodocumento", value = "existeId")
    private static String sqlExisteIdTipoDocumento;

    @SqlStatement(namespace = "tipodocumento", value = "existeTipoIdentificacion")
    private static String sqlExisteTipoIdentificacion;

    public RepositorioTipoDocumentoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(TipoDocumento tipoDocumento) {
        return this.customNamedParameterJdbcTemplate.crear(tipoDocumento, sqlCrearTipoDocumento);
    }

    @Override
    public void eliminar(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminarTipoDocumento, paramSource);
    }

    @Override
    public boolean existe(String tipoIdentificacion) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("tipoIdentificacion", tipoIdentificacion);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExisteTipoDocumento,
                paramSource, Boolean.class);
    }

    @Override
    public boolean existeId(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExisteIdTipoDocumento,
                paramSource, Boolean.class);
    }

    @Override
    public void actualizar(TipoDocumento tipoDocumento) {
        this.customNamedParameterJdbcTemplate.actualizar(tipoDocumento, sqlActualizarTipoDocumento);
    }

    @Override
    public boolean existeTipoIdentificacion(String tipoIdentificacion) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();

        paramSource.addValue("tipoIdentificacion", tipoIdentificacion);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlExisteTipoIdentificacion, paramSource, Boolean.class);
    }
}
