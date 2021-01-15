package com.ceiba.cliente.adaptador.repositorio;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.ceiba.cliente.modelo.entidad.Cliente;
import com.ceiba.cliente.puerto.repositorio.RepositorioCliente;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;

@Repository
public class RepositorioClienteMysql implements RepositorioCliente {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "cliente", value = "crear")
    private static String sqlCrearCliente;

    @SqlStatement(namespace = "cliente", value = "actualizar")
    private static String sqlActualizarCliente;

    @SqlStatement(namespace = "cliente", value = "eliminar")
    private static String sqlEliminarCliente;

    @SqlStatement(namespace = "cliente", value = "existe")
    private static String sqlExisteCliente;

    @SqlStatement(namespace = "cliente", value = "existeExcluyendoId")
    private static String sqlExisteExcluyendoIdCliente;

    @SqlStatement(namespace = "cliente", value = "existeId")
    private static String sqlExisteIdCliente;

    public RepositorioClienteMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Cliente cliente) {
        return this.customNamedParameterJdbcTemplate.crear(cliente, sqlCrearCliente);
    }

    @Override
    public void eliminar(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminarCliente, paramSource);
    }

    @Override
    public boolean existe(String numeroDocumento) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("numeroDocumento", numeroDocumento);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExisteCliente,
                paramSource, Boolean.class);
    }

    @Override
    public void actualizar(Cliente cliente) {
        this.customNamedParameterJdbcTemplate.actualizar(cliente, sqlActualizarCliente);
    }

    @Override
    public boolean existeExcluyendoId(Long id, String numeroDocumento) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        paramSource.addValue("numeroDocumento", numeroDocumento);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlExisteExcluyendoIdCliente, paramSource, Boolean.class);
    }

    @Override
    public boolean existeId(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExisteIdCliente,
                paramSource, Boolean.class);
    }

}
